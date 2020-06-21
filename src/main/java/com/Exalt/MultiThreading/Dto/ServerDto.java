package com.Exalt.MultiThreading.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDto {

    String id;
    int remainingCapacity;
    HashMap<String,Integer> Customers ;
}
