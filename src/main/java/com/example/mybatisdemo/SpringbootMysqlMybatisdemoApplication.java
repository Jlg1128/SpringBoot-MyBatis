package com.example.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"com.example.mybatisdemo"}, exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan({"com.example.mybatisdemo"})
//@EntityScan("com.example.mybatisdemo")
//@SpringBootApplication
@SpringBootApplication
@MapperScan("com.example.mybatisdemo.dao")
public class SpringbootMysqlMybatisdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMysqlMybatisdemoApplication.class, args);
	}
}
