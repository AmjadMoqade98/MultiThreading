package com.Exalt.MultiThreading.Domain.Dom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDom {
    String id;
    int remainingCapacity;
    boolean active = true;
    
}
