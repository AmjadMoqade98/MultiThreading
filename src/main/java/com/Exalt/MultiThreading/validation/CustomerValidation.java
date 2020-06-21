package com.Exalt.MultiThreading.validation;

import com.Exalt.MultiThreading.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    @Autowired
    CustomerRepository customerRepository ;

    public boolean validateCustomerId(String id) {
        if(customerRepository.findOne(id) == null) return false ;
        return true ;
    }
}
