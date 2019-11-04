package com.topwulian.service.impl;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.topwulian.dao.*;
import com.topwulian.model.*;
import net.bytebuddy.asm.Advice;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Component
public class AliyunSubDeviceService implements ApplicationRunner {


    @Autowired
    private AliyunDeivceDao aliyunDeivceDao;

    @Autowired
    private AliyunWlDeviceInfoDao aliyunWlDeviceInfoDao;

    @Autowired
    private DeviceTypeDao deviceTypeDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private DeviceGatherDao deviceGatherDao;

    @Autowired
    private MonpointsDao monpointsDao;


    @Autowired
    private MonpointsDeviceDao monpointsDeviceDao;

    @Autowired
    private DeviceConDao deviceConDao;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private DeviceThresholdDao deviceThresholdDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 身份
        String accessKey = "LTAI3jTQMjLamd0v";
        String accessSecret = "aOR1ZFUoJCKmiSUUQopZcwZDu0uei6";
        String regionId = "cn-shanghai";
        String uid = "1987344665882027";
        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";
        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);
        // 数据接收
        client.connect(messageToken -> {
             Message m = messageToken.getMessage();
            /*System.out.println("\ntopic="+m.getTopic());
            System.out.println("payload=" + new String(m.getPayload()));
            System.out.println("generateTime=" + m.getGenerateTime());
*/
            System.out.println("generateTime=" + m.getGenerateTime());
            AliyunInfoData(m.getTopic(),new String(m.getPayload()));

            // 此处标记CommitSuccess已消费，IoT平台会删除当前Message，否则会保留到过期时间
            return MessageCallback.Action.CommitSuccess;
        });
    }

    public void AliyunInfoData(String topic,String payload){

        String str = topic;
        String indexofStr = "/sys";
        String subStr = "";

        String productKey="";
        String DeviceName="";

        JSONObject json = JSONObject.fromObject(payload);

        if(str.indexOf(indexofStr) != -1){
            subStr = str.substring(5,str.length());
            int i = subStr.indexOf("/");
            productKey = subStr.substring(0,i);
            subStr = subStr.substring(i+1,subStr.length());
            DeviceName = subStr.substring(0,subStr.indexOf("/"));
        }else{
            subStr = str;
            subStr = subStr.substring(1,subStr.length());
            int i = subStr.indexOf("/");
            productKey = subStr.substring(0,i);
            subStr = subStr.substring(i+1,subStr.length());
            DeviceName = subStr.substring(0,subStr.indexOf("/"));
        }

        System.out.println("ProductKey:"+productKey+",DeviceName"+DeviceName);

        //通过产品key 和 设备名称查询数据
        List<AliyunDevice> alDeviceList = aliyunDeivceDao.getByNameAndKey(DeviceName,productKey);


        if(alDeviceList.size()>0){
            //查看设备类型 0:控制设备 1:采集数据设备
            String type = alDeviceList.get(0).getDeviceType();
            int farm_id = Integer.parseInt(alDeviceList.get(0).getFarmId());
            int user_id = Integer.parseInt(alDeviceList.get(0).getUserId());

            Farm farm = farmDao.getById(Long.parseLong(farm_id+""));
            String address = farm.getProvince()+farm.getCity()+farm.getDistrict();

            //添加监测点 查询是否存在.
            List<Monpoints> listMon = monpointsDao.getByMonInfo(user_id+"",farm_id+"",type);
            Long mid = 0L;
            boolean flag = false;
            if(listMon.size()>0){
                for(int i = 0; i < listMon.size(); i++){
                    //判断用户名称是否等于标识ID
                    if(DeviceName.equals(listMon.get(i).getMonpointsid())){
                        mid = listMon.get(i).getId();
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    //创建新的监测点
                    Monpoints monpoints = new Monpoints();
                    monpoints.setMonpointsName(farm.getName()+"监测点");
                    monpoints.setMonpointsAddress(address);
                    monpoints.setMonpointsid(DeviceName);
                    monpoints.setMonpointstype(type);//阿里云
                    monpoints.setFarmid(farm_id+"");
                    monpoints.setUserid(user_id+"");
                    monpoints.setCreateuser("ALY");
                    monpointsDao.save(monpoints);
                    mid = monpoints.getId();
                }
            }else{
                //创建新的监测点
                Monpoints monpoints = new Monpoints();
                monpoints.setMonpointsName(farm.getName()+"监测点");
                monpoints.setMonpointsDeviceName("控制设备");
                monpoints.setMonpointsAddress(address);
                monpoints.setMonpointsid(DeviceName);
                monpoints.setMonpointstype(type);//阿里云
                monpoints.setFarmid(farm_id+"");
                monpoints.setUserid(user_id+"");
                monpoints.setCreateuser("ALY");
                monpointsDao.save(monpoints);
                mid = monpoints.getId();
            }

            List<Map<String,Object>>  listDeviceInfo = aliyunWlDeviceInfoDao.getByDeviceId(alDeviceList.get(0).getId().toString(),type);
            if(type.equals("0")){
                //更新控制设备
                for(Map<String,Object> info : listDeviceInfo){
                    String devid = info.get("device_id").toString(); //设备标识
                    String dname = info.get("device_name").toString().trim(); //设备名称

                    String items = json.get("items").toString();

                    JSONObject jsonItem = JSONObject.fromObject(items);

                    if(payload.indexOf(devid) != -1){

                        String time = JSONObject.fromObject(jsonItem.get(devid)).get("time").toString();//时间
                        String value = JSONObject.fromObject(jsonItem.get(devid)).get("value").toString(); //设备值
                        DeviceCon deviceCon = new DeviceCon();
                        List<DeviceCon> listdeviceCon =  deviceConDao.getByInfo(farm_id,user_id,devid);
                        Long dconId = 0L;
                        if(listdeviceCon.size()>0){
                            deviceCon.setDeviceCon(devid);
                            deviceCon.setFarmId(farm_id+"");
                            deviceCon.setUserId(user_id+"");
                            if(value.equals("0") || value.equals("1")){
                                deviceCon.setStatus(value);
                            }else{
                                deviceCon.setStatus("0");
                            }
                            deviceCon.setOptime(new Date(Long.parseLong(time)));
                            deviceConDao.update(deviceCon);
                            dconId = listdeviceCon.get(0).getId();
                        }else{
                            deviceCon.setDeviceCon(devid);
                            deviceCon.setDeviceName(dname);
                            deviceCon.setFarmId(farm_id+"");
                            deviceCon.setUserId(user_id+"");
                            if(value.equals("0") || value.equals("1")){
                                deviceCon.setStatus(value);
                            }else{
                                deviceCon.setStatus("0");
                            }
                            deviceCon.setOptime(new Date(Long.parseLong(time)));
                            deviceCon.setCreateuser("admin");
                            deviceConDao.save(deviceCon);
                            dconId = deviceCon.getId();
                        }


                        List<MonpointsDeviceCon> listMonDeviceCon =  monpointsDeviceDao.getByDeviceConId(mid+"",dconId+"");
                        if(listMonDeviceCon.size()>0){

                        }else{
                            //设备关联监测点
                            MonpointsDeviceCon monpointsDeviceCon = new MonpointsDeviceCon();
                            monpointsDeviceCon.setMid(mid);
                            monpointsDeviceCon.setDeviceid(dconId);
                            monpointsDeviceDao.saveMonDeviceCon(monpointsDeviceCon);
                        }

                    }
                }
            }else{
                //更新设备数据
                int index = 1;
                for(Map<String,Object> info : listDeviceInfo){

                    String devid = info.get("device_id").toString(); //设备标识
                    String dname = info.get("device_name").toString().trim(); //设备名称
                    String dunit = info.get("device_unit").toString(); //设备单位

                    String items = json.get("items").toString();

                    JSONObject jsonItem = JSONObject.fromObject(items);

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    if(payload.indexOf(devid) != -1){
                        String time = JSONObject.fromObject(jsonItem.get(devid)).get("time").toString();//时间
                        String value = JSONObject.fromObject(jsonItem.get(devid)).get("value").toString(); //设备值
                        String datetime = df.format(new Date(Long.parseLong(time)));//上传时间

                        //通过名称查询是否存在该类型
                        List<DeviceType> deviceTypes = deviceTypeDao.selectByNameTypeInfo(dname);
                        Long typeId = 0L;
                        if (deviceTypes.size() > 0) {
                            typeId = deviceTypes.get(0).getId();
                        } else {
                            //创建设备类型
                            DeviceType deviceType = new DeviceType();
                            deviceType.setName(dname);
                            deviceType.setDescription(dname);
                            deviceType.setRemark("ALY");
                            deviceType.setOperator("admin");
                            deviceTypeDao.add(deviceType);
                            typeId = deviceType.getId(); //获取新添加类型的ID
                        }

                        //查询设备是否存在
                        List<Device> devices = deviceDao.getByInfo(farm_id, user_id, dname);
                        Long deviceID = 0L;
                        Boolean f = false;
                        if (devices.size() > 0) {
                            deviceID = devices.get(0).getId();
                            f = true;
                        } else {
                            //创建设备
                            Device device = new Device();
                            device.setSn(devid);
                            device.setName(dname);
                            device.setTypeId(Integer.parseInt(typeId + ""));
                            device.setFarmId(Integer.parseInt(farm_id + ""));
                            device.setLocation(index + "号位置");
                            device.setImage(index + ".jgp");
                            device.setInstallTime(new Date());
                            device.setState("1");
                            device.setStandard("1");
                            device.setManufacturer("ALY");
                            device.setUserId(Integer.parseInt(user_id + ""));
                            device.setSoftwareVersion("1.0");
                            device.setThresholdMin(Float.parseFloat("1"));
                            device.setThresholdMax(Float.parseFloat("99999"));
                            device.setRemark(dname);
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
                        deviceGather.setDeviceSn(devid);
                        deviceGather.setDeviceName(dname);
                        deviceGather.setDeviceType(typeId + "");
                        deviceGather.setBasicData(Float.parseFloat(value + ""));
                        deviceGather.setMeasurementUnitId(index);
                        deviceGather.setMeasurementUnitName(dunit);
                        deviceGather.setMeasureUnitType(dname);
                        deviceGather.setMonpointsId(mid+"");
                        deviceGather.setGatherTime(new Date());
                        deviceGather.setCreateTime(new Date());
                        deviceGather.setUpdateTime(new Date());
                        deviceGatherDao.save(deviceGather);

                        float valueF = Float.parseFloat(value + "");
                        if(f){
                            Float min = devices.get(0).getThresholdMin();
                            Float max = devices.get(0).getThresholdMax();

                            String remarks = "";
                            String thresholdValue = "";

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
                                deviceThreshold.setRemindDate(new Date(Long.parseLong(time)));
                                deviceThreshold.setStatus(1);
                                deviceThreshold.setMinthreshold(min+"");
                                deviceThreshold.setMaxthreshold(max+"");
                                deviceThreshold.setRemark(remarks);
                                deviceThreshold.setValue(valueF+"");
                                deviceThresholdDao.save(deviceThreshold);
                            }

                        }


                        index++;
                    }
                }
            }
        }



    }
}
