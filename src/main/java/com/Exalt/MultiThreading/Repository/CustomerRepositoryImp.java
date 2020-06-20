package com.Exalt.MultiThreading.Repository;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.mapping.AerospikeSimpleTypes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepositoryImp {

    @Autowired
    CustomerRepository customerRepository ;

    @Autowired
    AerospikeTemplate aerospikeTemplate ;

    public CustomerDao findOne(String id) {
        return customerRepository.findOne(id);
    }

    public CustomerDao save(CustomerDao customerDao) {
        return customerRepository.save(customerDao);
    }

    public void delete(String id){
        customerRepository.delete(id);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public Iterable<CustomerDao> findAll(){
        return customerRepository.findAll();
    }
}
