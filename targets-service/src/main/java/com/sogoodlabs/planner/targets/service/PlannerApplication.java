package com.sogoodlabs.planner.targets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.bus.events.common.channel.RealmsChannels;
import com.sogoodlabs.planner.bus.events.common.channel.TargetsChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(value = {RealmsChannels.class, TargetsChannels.class})
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
