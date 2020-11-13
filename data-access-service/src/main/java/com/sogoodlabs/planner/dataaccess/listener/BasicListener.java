package com.sogoodlabs.planner.dataaccess.listener;


import com.sogoodlabs.planner.data.common.data.entities.Realm;
import com.sogoodlabs.planner.dataaccess.data.RealmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BasicListener {

    private static final Logger log = Logger.getLogger(BasicListener.class.getName());

    @Autowired
    private RealmsRepository realmsRepository;

    @StreamListener("realms-events")
    public void realmsIn(@Payload Realm realm) {
        log.info("Creating realm: " + realm.getTitle());
        realmsRepository.save(realm);
    }

}
