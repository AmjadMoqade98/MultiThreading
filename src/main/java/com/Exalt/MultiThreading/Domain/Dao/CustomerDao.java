package com.Exalt.MultiThreading.Domain.Dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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