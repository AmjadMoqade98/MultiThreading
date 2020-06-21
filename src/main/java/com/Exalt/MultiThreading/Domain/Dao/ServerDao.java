package com.Exalt.MultiThreading.Domain.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDao {

    @Id
    String id ;
    int totalCap ;
    int remainingCap;
}
