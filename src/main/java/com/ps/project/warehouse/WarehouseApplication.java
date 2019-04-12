package com.ps.project.warehouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class WarehouseApplication {

	@Autowired
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);

	}

}
