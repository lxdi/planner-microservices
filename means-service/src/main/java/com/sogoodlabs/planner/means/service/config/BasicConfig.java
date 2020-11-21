package com.sogoodlabs.planner.means.service.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.means.service.controllers.MainController;
import com.sogoodlabs.planner.means.service.service.MeansCUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BasicConfig {

    @Bean
    public IMapper mapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new IMapper() {

            private Logger log = LoggerFactory.getLogger(IMapper.class.getName());

            @Override
            public String writeValueAsString(Object obj) {
                try{
                    return objectMapper.writeValueAsString(obj);
                } catch (Exception e){
                    log.warn("Error while writing to string obj {}", obj);
                    return null;
                }
            }
        };
    }

}
