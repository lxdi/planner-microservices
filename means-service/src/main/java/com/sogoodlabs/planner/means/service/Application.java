package com.sogoodlabs.planner.means.service;

import com.sogoodlabs.planner.bus.events.common.channel.MeansChannels;
import com.sogoodlabs.planner.bus.events.common.channel.MeansTargetsRelationsChannels;
import com.sogoodlabs.planner.bus.events.common.channel.RealmsChannels;
import com.sogoodlabs.planner.bus.events.common.channel.TargetsChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = {RealmsChannels.class, MeansChannels.class,
		TargetsChannels.class, MeansTargetsRelationsChannels.class})
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
