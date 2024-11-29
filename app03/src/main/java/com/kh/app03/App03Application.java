package com.kh.app03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class App03Application {

	public static void main(String[] args) {
		SpringApplication.run(App03Application.class, args);
	}

}
