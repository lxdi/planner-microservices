package com.sogoodlabs.planner.targets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.targets.service.streams.BasicMessagesStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(BasicMessagesStreams.class)
@EnableFeignClients
public class PlannerApplication {

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
	}

}
