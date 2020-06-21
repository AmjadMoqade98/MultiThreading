package com.Exalt.MultiThreading.Infrastructure;

import com.Exalt.MultiThreading.Domain.Dao.CustomerDao;
import com.Exalt.MultiThreading.Infrastructure.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.stereotype.Component;

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
