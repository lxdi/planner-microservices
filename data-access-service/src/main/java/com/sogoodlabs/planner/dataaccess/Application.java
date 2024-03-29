package com.sogoodlabs.planner.dataaccess;

import com.sogoodlabs.planner.bus.events.common.channel.MeansChannels;
import com.sogoodlabs.planner.bus.events.common.channel.MeansTargetsRelationsChannels;
import com.sogoodlabs.planner.bus.events.common.channel.RealmsChannels;
import com.sogoodlabs.planner.bus.events.common.channel.TargetsChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(value = {RealmsChannels.class, TargetsChannels.class, MeansChannels.class,
		MeansTargetsRelationsChannels.class})
@EntityScan("com.sogoodlabs.planner.data.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
