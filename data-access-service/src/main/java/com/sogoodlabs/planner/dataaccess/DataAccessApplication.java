package com.sogoodlabs.planner.dataaccess;

import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.common_mapper.IEntityById;
import com.sogoodlabs.planner.dataaccess.config.MessageStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(MessageStreams.class)
@EntityScan("com.sogoodlabs.planner.data.common.data.entities")
public class DataAccessApplication {


	@Bean
	public IEntityById entityById(){
		return new IEntityById(){
			@Override
			public Object get(long l, Class aClass) {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Bean
	public CommonMapper commonMapper(IEntityById entityById){
		com.sogoodlabs.common_mapper.Configuration mapperConfig = new com.sogoodlabs.common_mapper.Configuration();
		mapperConfig.mapEmptyFields = true;
		return new CommonMapper(entityById, mapperConfig);
	}

	public static void main(String[] args) {
		SpringApplication.run(DataAccessApplication.class, args);
	}

}
