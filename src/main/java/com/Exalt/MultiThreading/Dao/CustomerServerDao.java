package com.Exalt.MultiThreading.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServerDao {

    @Id
    int ServerId ;
    int CustomerId ;
    int Space ;

}
