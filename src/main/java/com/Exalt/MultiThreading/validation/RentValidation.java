package com.Exalt.MultiThreading.validation;

import org.springframework.stereotype.Component;

@Component
public class RentValidation {

    public boolean validateRentSpace(int space){
        if(space > 100) return false ;
        if(space < 1 ) return false ;
        return true ;
    }

}
