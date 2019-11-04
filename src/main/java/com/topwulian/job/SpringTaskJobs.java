package com.topwulian.job;

import com.alibaba.fastjson.JSON;
import com.topwulian.dao.*;
import com.topwulian.dto.Data;
import com.topwulian.dto.HttpResult;
import com.topwulian.model.*;
import com.topwulian.service.MonpointsService;
import com.topwulian.service.impl.HttpAPIService;
import net.bytebuddy.asm.Advice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.*;

/**
 * @Author: szz
 * @Date: 2019/1/4 上午12:07
 * @Version 1.0
 */
@Component
@Configuration
public class SpringTaskJobs {

    @Autowired
    private SysLogsDao sysLogsDao;

    @Autowired
    private HttpAPIService httpAPIService;

    @Autowired
    private SysYs7AccountDao sysYs7AccountDao;

    @Autowired
    private VedioDao vedioDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysStTokenInfoDao sysStTokenInfoDao;

    @Autowired
    private DeviceTypeDao deviceTypeDao;

    @Autowired
    private DeviceGatherDao deviceGatherDao;

    @Autowired
    private MonpointsDao monpointsDao;

    @Autowired
    private MonpointsDeviceDao monpointsDeviceDao;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeviceThresholdDao deviceThresholdDao;


    /**
     * 定时为萤石云账号获取视频设备的accessToken,因为这个token一周会过期一次
     * 接口参考地址:https://open.ys7.com/doc/zh/book/index/user.html
     * 每周一23点15分执行任务
     * @param
     * @return
     */
    @Scheduled(cron = "0 15 23 ? * MON")
    @Transactional
    public void getAccessTokenAtEveryWeek()throws Exception{

        SysLogs sysLogs=new SysLogs();
        sysLogs.setModule("定时任务");

        sysLogs.setFlag(true);
        sysLogs.setCreateTime(new Date());
        sysLogs.setUpdateTime(new Date());

        //萤石云获取摄像头控制key的接口,一周需要执行一次
        String url="https://open.ys7.com/api/lapp/token/get";

        //得到所有的萤石云开发账号
        List<SysYs7Account> sysYs7AccountList = sysYs7AccountDao.list(null, null, null);
        for (SysYs7Account sysYs7Account : sysYs7AccountList) {
            Map<String, Object> param=new HashMap<>();
            param.put("appKey",sysYs7Account.getAppKey());
            param.put("appSecret",sysYs7Account.getAppSecret());
            //这个接口不能频繁请求,为防止多账号情况下的请求次数过多,需要sleep
            Thread.sleep(30000);
            HttpResult httpResult = httpAPIService.doPost(url, param);
            Map mapMsg = JSON.parseObject(httpResult.getBody(), Map.class);
            Data data = JSON.parseObject(mapMsg.get("data").toString(),Data.class);
            sysYs7Account.setAccessToken(data.getAccessToken());
            //更新数据库
            sysYs7AccountDao.update(sysYs7Account);

            //根据userId查询该用户所拥有的全部摄像头,并将它们的accessKey也一起更新
            Map<String, Object> vedioParam = new HashMap<>();
            vedioParam.put("", sysYs7Account.getUserId());
            List<Vedio> vedioList = vedioDao.list(vedioParam, null, null);
            for (Vedio vedio : vedioList) {
                vedio.setAccessToken(sysYs7Account.getAccessToken());
                vedio.setUpdateTime(new Date());
                vedioDao.update(vedio);
            }

            //记录日志
            sysLogs.setRemark(data.toString());
            User user=new User();
            user.setId(new Long(sysYs7Account.getUserId()));
            sysLogs.setUser(user);
            sysLogsDao.save(sysLogs);
        }
    }

    /**
     * 项目启动后从数据库将各设备的阈值查询出来放入redis
     * 五分钟一次
     */
    //0 */5 * * * ?
    @Scheduled(cron = "0 */5 * * * ?")
    public void getDeviceThresholdFromRedis() {
        //redisTemplate.delete("deviceThreshold");
        Set deviceThreshold = redisTemplate.opsForHash().keys("deviceThreshold");
        if (deviceThreshold.size() < 1) {
            List<Device> deviceList = deviceDao.list(null, null, null);
            for (Device device : deviceList) {
                redisTemplate.opsForHash().put("deviceThreshold",device.getId(),device);
            }
        }
        System.out.println(deviceThreshold.size());
    }


    /**
     *获取列表信息
     *
     */
    //0 */5 * * * ?
    @Scheduled(cron = "0 0 0 1/2 * ?")
    //@Scheduled(cron = "0 0/1 * * * ? ")
    public void getDeviceInfo() throws Exception{
        List<SysStTokenInfo> tokenInfos = sysStTokenInfoDao.getByTokenInfo();
        if(tokenInfos.size()>0){
            for(SysStTokenInfo info:tokenInfos){
                //获取token信息
                String GET_TOKEN_URL = "https://console.sciento.top/jwt/login";
                JSONObject json = new JSONObject();
                json.put("loginType",2);
                json.put("password",info.getPassword());
                json.put("username",info.getUsername());
                String token_httpResult = HttpAPIService.post(GET_TOKEN_URL, json.toString());
                String code = JSONObject.fromObject(token_httpResult).get("code").toString();
                String token = "";
                if(code.equals("1")){
                    token=JSONObject.fromObject(token_httpResult).get("data").toString();
                    info.setToken(token);
                    //获取基地信息
                    String GET_USER_AREA_URL="http://light.sciento.top:9810/area/user";
                    String user_area_httpResult= HttpAPIService.doGet(GET_USER_AREA_URL,"jwtauthorization" ,token);
                    JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(user_area_httpResult).get("data").toString());
                    /*String id = jsonArray.getJSONObject(0).getString("id");
                    info.setAreaid(id);*/
                    sysStTokenInfoDao.updateTokenInfo(info);
                }
            }
        }
    }

    /**
     *获取列表信息
     *
     */
    //0 */5 * * * ?
    //@Scheduled(cron = "0 0 0 1/2 * ?")
    //@Scheduled(cron = "0 2/30 * * * ? ")
    //@Scheduled(cron = "* 0/1 * * * ? ")
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void getDeviceLogInfo() throws Exception{
        List<SysStTokenInfo> tokenInfos = sysStTokenInfoDao.getByTokenInfo();

        if(tokenInfos.size()>0){
            for(SysStTokenInfo info : tokenInfos) {
                String GET_USER_AREA_URL = "http://light.sciento.top:9810/device/collection/area?areaId=" + info.getAreaid();
                String result = HttpAPIService.doGet(GET_USER_AREA_URL, "jwtauthorization", info.getToken());
                if (!result.equals("")) {

                    JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(result).get("data").toString());
                    Integer farm_id = info.getFarmid();
                    Integer user_id = info.getUserid();

                    Farm farm = farmDao.getById(Long.parseLong(farm_id+""));
                    String address = farm.getProvince()+farm.getCity()+farm.getDistrict();

                    //添加监测点 查询是否存在.
                    List<Monpoints> listMon = monpointsDao.getByMonInfo(user_id.toString(),farm_id.toString(),"1");//1 为采集设备


                    User user =  userDao.getById(Long.parseLong(user_id+""));
                    Long mid = 0L;
                    boolean flag = false;
                    if(listMon.size()>0){
                        for(int i = 0; i < listMon.size(); i++){
                            //判断用户名称是否等于标识ID
                            String username = info.getUsername()+info.getUuid();
                            if(username.equals(listMon.get(i).getMonpointsid())){
                                mid = listMon.get(i).getId();
                                flag = true;
                                break;
                            }
                        }
                        if(!flag){
                            //创建新的监测点
                            Monpoints monpoints = new Monpoints();
                            monpoints.setMonpointsName(user.getNickname()+"监测点");
                            monpoints.setMonpointsAddress(address);
                            monpoints.setMonpointsid(info.getUsername()+info.getUuid());
                            monpoints.setMonpointstype("1");
                            monpoints.setFarmid(farm_id+"");
                            monpoints.setUserid(user_id+"");
                            monpoints.setCreateuser("gzst");
                            monpointsDao.save(monpoints);
                            mid = monpoints.getId();
                        }
                    }else{
                        //创建新的监测点
                        Monpoints monpoints = new Monpoints();
                        monpoints.setMonpointsName(user.getNickname()+"监测点");
                        monpoints.setMonpointsAddress(address);
                        monpoints.setMonpointsid(info.getUsername()+info.getUuid());
                        monpoints.setMonpointstype("1");//广州赛通的设备监测点
                        monpoints.setFarmid(farm_id+"");
                        monpoints.setUserid(user_id+"");
                        monpoints.setCreateuser("gzst");
                        monpointsDao.save(monpoints);
                        mid = monpoints.getId();
                    }


                    for (int i = 0; i < jsonArray.size(); i++) {
                        String lastestData = jsonArray.getJSONObject(i).getString("lastestData").toString();//实际值
                        String typename = jsonArray.getJSONObject(i).getString("typeName").toString();//类型名称
                        String unit = jsonArray.getJSONObject(i).getString("unit").toString(); //单位
                        String type = jsonArray.getJSONObject(i).getString("type").toString(); //type //sn
                        String lastestTime = jsonArray.getJSONObject(i).getString("lastestTime").toString(); //时间


                        int index = i + 1;
                        //通过名称查询是否存在该类型
                        List<DeviceType> deviceTypes = deviceTypeDao.selectByNameTypeInfo(typename);
                        Long typeId = 0L;
                        if (deviceTypes.size() > 0) {
                            typeId = deviceTypes.get(0).getId();
                        } else {
                            //创建设备类型
                            DeviceType deviceType = new DeviceType();
                            deviceType.setName(typename);
                            deviceType.setDescription(typename);
                            deviceType.setRemark("备注信息");
                            deviceType.setOperator("admin");
                            deviceTypeDao.add(deviceType);
                            typeId = deviceType.getId(); //获取新添加类型的ID
                        }

                        //查询设备是否存在
                        List<Device> devices = deviceDao.getByInfo(farm_id, user_id, typename + "传感器");
                        Long deviceID = 0L;
                        Boolean f = false;
                        if (devices.size() > 0) {
                            deviceID = devices.get(0).getId();
                            f = true;
                        } else {
                            //创建设备
                            Device device = new Device();
                            device.setSn(type);
                            device.setName(typename + "传感器");
                            device.setTypeId(Integer.parseInt(typeId + ""));
                            device.setFarmId(Integer.parseInt(farm_id + ""));
                            device.setLocation(typename + index + "位置");
                            device.setImage(index + ".jgp");
                            device.setInstallTime(new Date());
                            device.setState("1");
                            device.setStandard("1");
                            device.setManufacturer("guangzhou");
                            device.setUserId(Integer.parseInt(user_id + ""));
                            device.setSoftwareVersion("1.0");
                            device.setThresholdMin(Float.parseFloat("1"));
                            device.setThresholdMax(Float.parseFloat("99999"));
                            device.setRemark(typename + "传感器");
                            device.setOperator("admin");
                            deviceDao.add(device);
                            deviceID = device.getId();//获取新添加设备的ID
                        }



                        List<MonpointsDevice> listMonDevice =  monpointsDeviceDao.getByDeviceId(mid+"",deviceID+"");
                        if(listMonDevice.size()>0){

                        }else{
                            //设备关联监测点
                            MonpointsDevice monpointsDevice = new MonpointsDevice();
                            monpointsDevice.setMid(mid);
                            monpointsDevice.setDeviceid(deviceID);
                            monpointsDeviceDao.saveMonDevice(monpointsDevice);
                        }

                        //添加采集信息
                        DeviceGather deviceGather = new DeviceGather();
                        deviceGather.setDeviceId(Integer.parseInt(deviceID + ""));
                        deviceGather.setDeviceSn(type);
                        deviceGather.setDeviceName(typename + "传感器");
                        deviceGather.setDeviceType(typeId + "");
                        deviceGather.setBasicData(Float.parseFloat(lastestData + ""));
                        deviceGather.setMeasurementUnitId(index);
                        deviceGather.setMeasurementUnitName(unit);
                        deviceGather.setMeasureUnitType(typename);
                        deviceGather.setMonpointsId(mid+"");
                        deviceGather.setGatherTime(new Date());
                        deviceGather.setCreateTime(new Date());
                        deviceGather.setUpdateTime(new Date());
                        deviceGatherDao.save(deviceGather);


                        float valueF = Float.parseFloat(lastestData + "");
                        if(f){
                            Float min = devices.get(0).getThresholdMin();
                            Float max = devices.get(0).getThresholdMax();

                            String remarks = "";

                            if(valueF > min){
                                if(valueF > max){
                                    remarks = "数值大于阈值";
                                }
                            }else{
                                remarks = "数值小于阈值";
                            }
                            //判断数据正常则为不用创建预警数据
                            if(!remarks.equals("")){
                                DeviceThreshold deviceThreshold = new DeviceThreshold();
                                deviceThreshold.setMid(mid+"");
                                deviceThreshold.setDeviceId(deviceID+"");
                                deviceThreshold.setRemindDate(new Date(Long.parseLong(lastestTime)));
                                deviceThreshold.setStatus(1);
                                deviceThreshold.setMinthreshold(min+"");
                                deviceThreshold.setMaxthreshold(max+"");
                                deviceThreshold.setRemark(remarks);
                                deviceThreshold.setValue(valueF+"");
                                deviceThresholdDao.save(deviceThreshold);
                            }

                        }
                    }
                }
            }

        }
    }








}
