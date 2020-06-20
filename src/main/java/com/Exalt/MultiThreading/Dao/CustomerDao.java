package com.Exalt.MultiThreading.Dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDao {

    @Id
    String id;
    String name;
    int reservedSpace;

}