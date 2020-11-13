package com.sogoodlabs.planner.dataaccess;

import com.sogoodlabs.planner.dataaccess.config.MessageStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MessageStreams.class)
@EntityScan("com.sogoodlabs.planner.data.common.data.entities")
public class DataAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAccessApplication.class, args);
	}

}
