package com.sogoodlabs.planner.dataaccess.config;

import com.sogoodlabs.common_mapper.CommonMapper;
import com.sogoodlabs.common_mapper.IEntityById;
import com.sogoodlabs.planner.data.model.Mean;
import com.sogoodlabs.planner.data.model.Realm;
import com.sogoodlabs.planner.data.model.Target;
import com.sogoodlabs.planner.dataaccess.data.MeansRepository;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import com.sogoodlabs.planner.dataaccess.data.TargetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMapperConfig {

    @Autowired
    private TargetsRepository targetsRepository;

    @Autowired
    private RealmsRepository realmsRepository;

    @Autowired
    private MeansRepository meansRepository;

    @Bean
    public IEntityById entityById(){
        return (IEntityById<String>) (id, aClass) -> {
            if(aClass == Realm.class){
                return realmsRepository.findById(id)
                        .orElseThrow(() -> new NullPointerException("Realm "+ id +" not found while mapping"));
            }
            if(aClass == Target.class){
                return targetsRepository.findById(id)
                        .orElseThrow(() -> new NullPointerException("Target "+ id +" not found while mapping"));
            }
            if(aClass == Mean.class){
                return meansRepository.findById(id)
                        .orElseThrow(() -> new NullPointerException("Mean "+ id +" not found while mapping"));
            }
            throw new UnsupportedOperationException();
        };
    }

    @Bean
    public CommonMapper commonMapper(IEntityById entityById){
        com.sogoodlabs.common_mapper.Configuration mapperConfig = new com.sogoodlabs.common_mapper.Configuration();
        mapperConfig.mapEmptyFields = true;
        return new CommonMapper(entityById, mapperConfig);
    }

}
