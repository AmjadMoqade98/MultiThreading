package com.Exalt.MultiThreading.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    String id;
    String name;
    int reservedSpace;
//    HashMap<Integer,Integer> Servers ;

}
