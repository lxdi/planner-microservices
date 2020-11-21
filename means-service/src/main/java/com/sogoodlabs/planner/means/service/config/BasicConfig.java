package com.sogoodlabs.planner.means.service.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sogoodlabs.planner.data.model.IMapper;
import com.sogoodlabs.planner.means.service.controllers.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class BasicConfig {

    @Bean
    public IMapper mapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new IMapper() {

            private Logger log = Logger.getLogger(IMapper.class.getName());

            @Override
            public String writeValueAsString(Object obj) {
                try{
                    return objectMapper.writeValueAsString(obj);
                } catch (Exception e){
                    log.warning("Error while writing to string obj " + obj);
                    return null;
                }
            }
        };
    }

}
