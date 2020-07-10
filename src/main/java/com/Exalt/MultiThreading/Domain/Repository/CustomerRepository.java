package com.Exalt.MultiThreading.Domain.Repository;

import com.Exalt.MultiThreading.Domain.Dao.CustomerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;


public interface CustomerRepository extends AerospikeRepository<CustomerDao, String> {

}
