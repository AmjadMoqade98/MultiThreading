package com.Exalt.MultiThreading.Domain.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDao {

    @Id
    String id ;
    @Field("nam")
    String name;
    int reservedSpace;

}