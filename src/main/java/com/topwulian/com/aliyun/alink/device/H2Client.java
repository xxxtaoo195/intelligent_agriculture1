package com.topwulian.com.aliyun.alink.device;

import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

public class H2Client{

    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {

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

        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);

        // 数据接收
        client.connect(messageToken -> {
            Message m = messageToken.getMessage();
            System.out.println("\ntopic="+m.getTopic());
            System.out.println("payload=" + new String(m.getPayload()));
            System.out.println("generateTime=" + m.getGenerateTime());
            // 此处标记CommitSuccess已消费，IoT平台会删除当前Message，否则会保留到过期时间
            return MessageCallback.Action.CommitSuccess;
        });


    }

}
