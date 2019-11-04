package com.topwulian.com.aliyun.alink.device;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.iot.model.v20180120.PubRequest;
import com.aliyuncs.iot.model.v20180120.PubResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;

public class testPub {

    public static void main(String[] args) {

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
        jsonDate.put("PowerSwitch_3",1);
        jsonObject.put("params",jsonDate);

        System.out.println(jsonObject.toString());

        PubRequest request = new PubRequest();
        request.setProductKey("a1gGNMV0QkF");
        request.setMessageContent(Base64.encodeBase64String(jsonObject.toString().getBytes()));
        request.setTopicFullName("/sys/a1gGNMV0QkF/tpstkz/thing/service/property/set");
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
