package com.topwulian.com.aliyun.alink.device;


import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testjson {

    public static void main(String[] args){
        String sss = "{\"deviceType\":\"EnvironmentMonitoring\",\"iotId\":\"I1vN3t0s953Nm8fWDVcc000100\",\"productKey\":\"a1Nd9HwQYVF\",\"gmtCreate\":1564453107158,\"deviceName\":\"testDemo\",\"items\":{\"UV\":{\"value\":0.0,\"time\":1564453107164},\"PM10\":{\"value\":38,\"time\":1564453107164},\"PM1D0\":{\"value\":25,\"time\":1564453107164},\"PowerSwitch_1\":{\"value\":0,\"time\":1564453107164},\"PowerSwitch_2\":{\"value\":0,\"time\":1564453107164},\"PM2D5\":{\"value\":12,\"time\":1564453107164},\"PowerSwitch_3\":{\"value\":0,\"time\":1564453107164},\"CO2Value\":{\"value\":51,\"time\":1564453107164}}}";

        JSONObject json = JSONObject.fromObject(sss);

        String  deviceType = json.get("deviceType").toString();
        String  iotId = json.get("iotId").toString();
        String  productKey = json.get("productKey").toString();
        String  gmtCreate = json.get("gmtCreate").toString();
        String  deviceName = json.get("deviceName").toString();


        String  items = json.get("items").toString();

        JSONObject jsonItem = JSONObject.fromObject(items);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for(Object str:jsonItem.keySet()){
            String time = JSONObject.fromObject(jsonItem.get(str)).get("time").toString();
            System.out.println(str + ":" +JSONObject.fromObject(jsonItem.get(str)).get("value").toString()+":"+df.format(new Date(Long.parseLong(time))));

        }








    }
}
