package com.Exalt.MultiThreading.Domain.validation;

import com.Exalt.MultiThreading.Domain.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerValidation {

    @Autowired
    ServerRepository serverRepository ;

    public boolean validateServerId(String id) {
        if(serverRepository.findOne(id) == null) return false ;
        return true ;
    }
}
