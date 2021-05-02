package com.shop.springboot;

import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.User;
import com.shop.springboot.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);
	}

}
