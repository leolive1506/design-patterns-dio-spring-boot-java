package com.santam.designpatters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesignPattersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPattersApplication.class, args);
	}

}
