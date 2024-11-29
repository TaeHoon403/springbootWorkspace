package com.kh.prac01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class Prac01Application {

	public static void main(String[] args) {
		SpringApplication.run(Prac01Application.class, args);
	}

}
