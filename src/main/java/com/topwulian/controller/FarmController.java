package com.topwulian.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.iot.api.util.StringUtil;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.iot.model.v20180120.PubRequest;
import com.aliyuncs.iot.model.v20180120.PubResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Maps;
import com.topwulian.dao.*;
import com.topwulian.dto.FarmDto;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableHandler.CountHandler;
import com.topwulian.page.table.PageTableHandler.ListHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.DeviceService;
import com.topwulian.service.FarmEntQuaService;
import com.topwulian.service.impl.FarmEntQuaServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private DeviceLogDao deviceLogDao;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private VedioDao vedioDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private FarmEntQuaService farmEntQuaService;

    @Autowired
    private MonpointsDao monpointsDao;

    @Autowired
    private DeviceConDao deviceConDao;

    @Autowired
    private AliyunDeivceDao aliyunDeivceDao;

    @Autowired
    private DeviceThresholdDao deviceThresholdDao;


    @Value("${file.aliyun.bucketName}")
    private String bucketName;

    @GetMapping("/info/{noticeRows}/{deviceLogRows}")
    @ApiOperation(value = "我的基地页面显示各种详情")
    public FarmDto farmInfo(@PathVariable Integer noticeRows,@PathVariable Integer deviceLogRows){
        FarmDto farmDto=new FarmDto();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword(null);
        farmDto.setUser(user);
        //农情通知,显示最近条数,由前端传入
        /*List<Notice> noticeList = noticeDao.recentlyNotice(0, noticeRows);*/
        //设备报警
        List<DeviceLog> deviceLogList = deviceLogDao.deviceNotice(0, deviceLogRows, user.getId());

        //根据用户id获取拥有的基地,在farm_user表中配置了用户和基地的关系
        //前端默认显示用户拥有的第一个基地,可以通过下拉列表来切换基地

        //设备信息
        //基地id默认就是当前拥有的第一个基地,根据前端传递过来的farmId进行切换
        //根据用户获取拥有的农场
        Long farmId = null;
        List<Farm> farmList = farmDao.listByUserId(user.getId());
        if (farmList != null && farmList.size() > 0) {
            Farm farm = farmList.get(0);
            farmId=farm.getId();
            farmDto.setFarm(farm);
            List<Device> deviceList = deviceDao.getByFarmId(farmId);
            //当前基地的设备实时数据
            /*List<DeviceGather> deviceGathers=deviceService.getRealTimeDataByFarmId(farmId);*/
            //当前基地的摄像头列表
            List<Vedio> vedioList=vedioDao.getVedioListByFarmId(farmId);
            //查询基地监测点
            List<Monpoints> listmonpoints = monpointsDao.getByUserAndFarmInfo(user.getId()+"",farmId+"");
            for(Monpoints mon : listmonpoints){

                List<DeviceCon> deviceConList = deviceService.listDeviceCon(mon.getMonpointsid());
                mon.setDeviceConList(deviceConList);


               /* List<DeviceGather> deviceGatherList = deviceService.getGatherInfo(mon.getMonpointsid());
                mon.setDeviceGatherList(deviceGatherList);*/
            }

            List<DeviceThreshold> deviceThresholdList = deviceThresholdDao.listThresholdInfo(farmId.toString(),user.getId().toString());



            farmDto.setDeviceList(deviceList);
            farmDto.setDeviceThresholdList(deviceThresholdList);
           /* farmDto.setRealTimeDataList(deviceGathers);*/
            farmDto.setVedioList(vedioList);
            farmDto.setMonpointsList(listmonpoints);
        }

        /*farmDto.setRecentlyNoticeList(noticeList);*/
        farmDto.setDeviceNoticeList(deviceLogList);

        return farmDto;
    }



    @GetMapping("/monpoint/{noticeRows}/{deviceLogRows}")
    @ApiOperation(value = "我的基地页面显示各种详情")
    public FarmDto farmInfo2(@PathVariable Integer noticeRows,@PathVariable Integer deviceLogRows){
        FarmDto farmDto=new FarmDto();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword(null);
        farmDto.setUser(user);

        //设备报警
        List<DeviceLog> deviceLogList = deviceLogDao.deviceNotice(0, deviceLogRows, user.getId());


        //设备信息
        //基地id默认就是当前拥有的第一个基地,根据前端传递过来的farmId进行切换
        //根据用户获取拥有的农场
        Long farmId = null;
        List<Farm> farmList = farmDao.listByUserId(user.getId());
        if (farmList != null && farmList.size() > 0) {
            Farm farm = farmList.get(0);
            farmId=farm.getId();
            farmDto.setFarm(farm);


            //查询基地监测点
            List<Monpoints> listmonpoints = monpointsDao.getByUserAndFarmInfo(user.getId()+"",farmId+"");
            for(Monpoints mon : listmonpoints){


               List<DeviceGather> deviceGatherList = deviceService.getGatherInfoList(mon.getId().toString(),mon.getMonpointsid());
               mon.setDeviceGatherList(deviceGatherList);
            }
            farmDto.setMonpointsList(listmonpoints);

        }

        return farmDto;
    }



    @PostMapping
    @ApiOperation(value = "保存")
    @Transactional
    public Farm save(@RequestBody Farm farm){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //给基地设置管理员,其实就是farm中的userId
        farm.setUserId(user.getId());
        //mybatis保存到数据库后获取主键
        farmDao.save(farm);
        //同时设置基地和用户的关联关系
        List<Long> farmIds=new ArrayList<>();
        farmIds.add(farm.getId());
        userDao.saveUserFarms(user.getId(),farmIds);
        return farm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Farm get(@PathVariable Long id) {
        return farmDao.getById(id);
    }

    @GetMapping("/updateinfo")
    @ApiOperation(value = "修改")
    public Farm get() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        List<FarmEntQua> listFarmEntQua = farmEntQuaService.getImgInfoByFramID(farmList.get(0).getId());

        Farm farm = farmDao.getById(farmList.get(0).getId());

        farm.setListFarmEntQua(listFarmEntQua);

        return farm;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Farm update(@RequestBody Farm farm) {
        farmDao.update(farm);
        return farm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return farmDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Farm> list(PageTableRequest request) {
                return farmDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @Transactional
    public void delete(@PathVariable Long id) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        farmDao.delete(id);
        userDao.deleteUserIdAndFarmId(user.getId(),id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    public List<Farm> farms() {
        return farmDao.list(Maps.newHashMap(), null, null);
    }

    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id去user_farm中间表获取拥有的基地")
    public List<Farm> farms(Long userId) {
        return farmDao.listByUserId(userId);
    }



    @RequestMapping("/updateFarmInfo")
    @ApiOperation(value = "修改")
    public RespEntiry updateFarmInfo(HttpServletRequest request,@RequestParam(name = "img") MultipartFile[] uploadFiles) {

        String url = "";

        List<String> listUrlList = new ArrayList<String>();

        //上传企业资质图片
        for (MultipartFile uploadFile : uploadFiles) {
            String originalFilename = uploadFile.getOriginalFilename();
            //获取图片后缀
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //生成图片名称
            String imageName = IdUtil.simpleUUID();
            //上传SSO返回图片url地址
            try {
                url = file_upload(imageName+"."+extName, uploadFile.getInputStream());
                listUrlList.add(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String area = request.getParameter("area");
        String city = request.getParameter("city");
        String createTime = request.getParameter("createTime");
        String district = request.getParameter("district");
        String id = request.getParameter("id");
        String linkman = request.getParameter("linkman");
        String mainCrop = request.getParameter("mainCrop");
        String name = request.getParameter("name");
        //String position = request.getParameter("position");
        String province = request.getParameter("province");
        String remark = request.getParameter("remark");
        String telephone = request.getParameter("telephone");
        String type = request.getParameter("type");
        String plantstd = request.getParameter("plantstd");


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Farm farm = new Farm();
        farm.setArea(Double.parseDouble(area));
        farm.setCity(city);
        Date date = null;
        try {
            date = df.parse(createTime);
            farm.setCreateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        farm.setDistrict(district);
        farm.setId(Long.parseLong(id));
        farm.setLinkman(linkman);
        farm.setMainCrop(mainCrop);
        farm.setName(name);
        farm.setProvince(province);
        farm.setRemark(remark);
        farm.setTelephone(telephone);
        farm.setType(type);
        farm.setPlantstd(plantstd);
        farmDao.update(farm);

        for(String imgUrl : listUrlList){
            FarmEntQua farmEntQua = new FarmEntQua();
            farmEntQua.setImgURL(imgUrl);
            farmEntQua.setFarmid(Integer.parseInt(id));
            farmEntQuaService.save(farmEntQua);
        }
        return RespEntiry.success();
    }

    private String file_upload(String filename, InputStream in){
        ossClient.putObject(bucketName, "qyzz/"+filename, in);
        String url = getUrl("qyzz/" + filename);
        return url;
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    private String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    @RequestMapping("/getGatherInfo")
    public FarmDto getGatherInfo(HttpServletRequest request) {
        String monpointsid = request.getParameter("monpointsid");
        String type = request.getParameter("type");
        FarmDto farmDto = new FarmDto();
        if(type.equals("0")){
            List<Monpoints> monpointsList = monpointsDao.getByMid(monpointsid);
            if(monpointsList.size()>0){
                List<DeviceGather> deviceGatherList = deviceService.getGatherInfoList(monpointsList.get(0).getId().toString(),monpointsid);
                farmDto.setRealTimeDataList(deviceGatherList);
            }else{
                List<DeviceGather> deviceGatherList = deviceService.getGatherInfo(monpointsid);
                farmDto.setRealTimeDataList(deviceGatherList);
            }
        }else{
            if(StringUtil.isEmpty(monpointsid)){
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                List<Farm> farmList = farmDao.listByUserId(user.getId());

                List<Monpoints> listmon = new ArrayList<Monpoints>();

                List<Monpoints> listmonpoints = monpointsDao.getByUserAndFarmInfo(user.getId()+"",farmList.get(0).getId()+"");
                for(Monpoints mon : listmonpoints){
                    if(mon.getMonpointstype().equals("0")){
                        List<DeviceCon> deviceConList = deviceService.listDeviceCon(mon.getMonpointsid());
                        mon.setDeviceConList(deviceConList);
                        listmon.add(mon);
                    }
                }

                farmDto.setMonpointsList(listmon);
            }else {
                List<Monpoints> monpointsList = monpointsDao.getByMid(monpointsid);
                List<DeviceCon> deviceConList = deviceService.listDeviceCon(monpointsid);
                monpointsList.get(0).setDeviceConList(deviceConList);
                farmDto.setMonpointsList(monpointsList);
            }
        }
        return farmDto;
    }

    @RequestMapping("/deviceCon")
    public RespEntiry deviceCon(HttpServletRequest request) {
        String con = request.getParameter("con");
        int i = con.indexOf("|");
        String deviceName = con.substring(0,i);
        String deviceId = con.substring(i+1,con.length());

        /*DeviceCon deviceCon =  deviceConDao.getById(Long.parseLong(deviceId));
        deviceCon.setStatus("0");
        deviceCon.setOptime(new Date());
        deviceCon.setDeviceCon("PowerSwitch_3");*/


        List<AliyunDevice> aliyunDeviceList = aliyunDeivceDao.getByDeviceInfoMid(deviceId);
        DeviceConAliyun(aliyunDeviceList.get(0).getProductKey(),aliyunDeviceList.get(0).getDeviceName(),"PowerSwitch_3");//先停

        try {
            Thread.sleep(1500); //1000 毫秒，也就是1秒.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        DeviceConAliyun(aliyunDeviceList.get(0).getProductKey(),aliyunDeviceList.get(0).getDeviceName(),deviceName);//在重新发送最新状态
        return RespEntiry.success();
    }

    public void DeviceConAliyun(String productKey,String deviceName,String deviceCon){
        String accessKey = "LTAI3jTQMjLamd0v";
        String accessSecret = "aOR1ZFUoJCKmiSUUQopZcwZDu0uei6";
        try {
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        DefaultAcsClient client = new DefaultAcsClient(profile); //初始化SDK客户端


        JSONObject jsonObject = new JSONObject();

        JSONObject jsonDate = new JSONObject();
        jsonDate.put(deviceCon,1);
        jsonObject.put("params",jsonDate);

        System.out.println(jsonObject.toString());

        PubRequest request = new PubRequest();
        request.setProductKey(productKey);
        request.setMessageContent(Base64.encodeBase64String(jsonObject.toString().getBytes()));
        String topic = "/sys/"+productKey+"/"+deviceName+"/thing/service/property/set";
        request.setTopicFullName(topic);
        request.setQos(0); //目前支持QoS0和QoS1
        try
        {
            PubResponse response = client.getAcsResponse(request);
            System.out.println(response.getSuccess());
            System.out.println(response.getErrorMessage());
        }
        catch (ServerException e)
        {
            e.printStackTrace();
        }
        catch (ClientException e)
        {
            e.printStackTrace();
        }
    }



    



}
