package com.topwulian.com.aliyun.alink.device;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class GetAliyunInfo {
    //@Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("start  ---------------------");
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
        });

       MqttSample mqttSample = new MqttSample(productKey,deviceName);

        //订阅
        mqttSample.subscribe(subTopic);

        // topic订阅。订阅成功后，即可在建连时的回调接口中收到消息
        CompletableFuture subFuture = client.subscribe(subTopic);
        System.out.println("sub result : " + subFuture.get());
    }

    
}
