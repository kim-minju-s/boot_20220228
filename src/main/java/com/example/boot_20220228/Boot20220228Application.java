package com.example.boot_20220228;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@SpringBootApplication

// 임의로 만들 컨트롤러의 위치를 지정
@ComponentScan(basePackages = {"com.example.controller", "com.example.service"})

@EnableMongoHttpSession(collectionName = "session", maxInactiveIntervalInSeconds = 1800)
public class Boot20220228Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot20220228Application.class, args);

	}

	// @Bean => 프로그램이 구동되기 전에 미리 만들어지는 객체
	// JacksonMongoSessionConverter mongoSessionConverter = new JacksonMongoSessionConverter();
	// 몽고DB에서 attr을 쉽게 확인하기 위해서, 객체, 배열 X 
	// @Bean
	// public JacksonMongoSessionConverter mongoSessionConverter(){
	// 	return new JacksonMongoSessionConverter();
	// }

	// attr의 값이 byte[]로 보여짐. 객체, 배열.. 가능
	@Bean
	public JdkMongoSessionConverter mongoSessionConverter(){
		return new JdkMongoSessionConverter(Duration.ofMinutes(30));
	}

}
