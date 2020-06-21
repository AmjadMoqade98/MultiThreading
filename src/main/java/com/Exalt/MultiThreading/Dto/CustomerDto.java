package com.Exalt.MultiThreading.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    String id;
    String name;
    int reservedSpace;
    HashMap<String,Integer> Servers ;

}
