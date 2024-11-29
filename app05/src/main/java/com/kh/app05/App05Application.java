package com.kh.app05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class App05Application {

	public static void main(String[] args) {
		SpringApplication.run(App05Application.class, args);
	}

}
