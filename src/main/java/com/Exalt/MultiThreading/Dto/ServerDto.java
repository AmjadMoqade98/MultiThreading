package com.Exalt.MultiThreading.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDto {

    String id;
    int remainingCapacity;
    //HashMap<Integer,Integer> Customers ;
}
