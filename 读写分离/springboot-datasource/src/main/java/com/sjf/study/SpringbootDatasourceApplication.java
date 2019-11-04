package com.sjf.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author klin
 */
@SpringBootApplication
@MapperScan("com.sjf.study.dao")
public class SpringbootDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatasourceApplication.class, args);
	}

}
