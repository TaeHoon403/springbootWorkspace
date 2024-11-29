package com.kh.app04;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class App04Application {

	public static void main(String[] args) {
		SpringApplication.run(App04Application.class, args);
	}

}
