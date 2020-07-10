package com.Exalt.MultiThreading.Domain.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    String id;
    int remainingCapacity;
    boolean active = true;
}
