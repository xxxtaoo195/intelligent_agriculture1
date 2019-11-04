package com.topwulian.com.aliyun.alink.device;

public class AliyunSubDeviceService {
    private static AliyunSubDeviceService ourInstance = new AliyunSubDeviceService();

    public static AliyunSubDeviceService getInstance() {
        return ourInstance;
    }

    private AliyunSubDeviceService() {
    }
}
