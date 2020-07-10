package com.Exalt.MultiThreading.Domain.validation;

import com.Exalt.MultiThreading.Domain.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    @Autowired
    CustomerRepository customerRepository ;

    public boolean validateCustomerId(String id) {
        System.out.println(id);
        if(customerRepository.findOne(id) == null) return false ;
        return true ;
    }
}