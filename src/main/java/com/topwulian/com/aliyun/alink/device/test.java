package com.topwulian.com.aliyun.alink.device;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class test {


    public static void main(String[] args) {

       /* System.out.println("start  ---------------------");
        // 阿里云accessKey
        String accessKey = "LTAI3jTQMjLamd0v";
        // 阿里云accessSecret
        String accessSecret = "aOR1ZFUoJCKmiSUUQopZcwZDu0uei6";
        // regionId
        String regionId = "cn-shanghai";
        // 阿里云uid
        String uid = "1987344665882027";
        // endPoint:  https://${uid}.iot-as-http2.${region}.aliyuncs.com
        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";


        String productKey = "a1Nd9HwQYVF";
        String deviceName = "testDemo";
        String deviceSecret = "6ekbYpgQkQLRkdQhs2cuNvAvWpCA8wvw";

        String subTopic = "/" + productKey + "/" + deviceName + "/user/get";

        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);

        // 数据接收
        client.connect(messageToken -> {
            System.out.println("start  ---------------------1");
            Message m = messageToken.getMessage();
            System.out.println("receive message from " + m);
            System.out.println("\ntopic="+m.getTopic());
            System.out.println("payload=" + new String(m.getPayload()));
            System.out.println("generateTime=" + m.getGenerateTime());

            return MessageCallback.Action.CommitSuccess;
        });*/

      /*  String str = "/a1Nd9HwQYVF/testDemo/thing/event/property/post";
        String indexofStr = "/sys";
        String subStr = "";
        if(str.indexOf(indexofStr) != -1){
            System.out.println("字符串" + str + "中有" + indexofStr);

            System.out.println(str.substring(5,str.length()));
            subStr = str.substring(5,str.length());
            int i = subStr.indexOf("/");
            String productKey = subStr.substring(0,i);
            subStr = subStr.substring(i+1,subStr.length());
            String DeviceName = subStr.substring(0,subStr.indexOf("/"));



        }else{
            subStr = str;
            subStr = subStr.substring(1,subStr.length());
            int i = subStr.indexOf("/");
            String productKey = subStr.substring(0,i);
            subStr = subStr.substring(i+1,subStr.length());
            String DeviceName = subStr.substring(0,subStr.indexOf("/"));
        }*/

      String sss = "ssssss|123";
        int i = sss.indexOf("|");
        System.out.println(sss.substring(0,i));
        System.out.println(sss.substring(i+1,sss.length()));
    }
}
