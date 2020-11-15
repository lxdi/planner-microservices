package com.sogoodlabs.planner.targets.service;

import com.sogoodlabs.planner.targets.service.streams.BasicMessagesStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(BasicMessagesStreams.class)
@EnableFeignClients
public class PlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
	}

}
