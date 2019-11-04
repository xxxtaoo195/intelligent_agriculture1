package com.topwulian;


import com.topwulian.com.aliyun.alink.iot.SimpleClient4IOT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * http://localhost:8080/swagger-ui.html
 * @author 小威老师 xiaoweijiagou@163.com
 *
 *         2017年4月18日
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class ServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
