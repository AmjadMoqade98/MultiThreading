package com.Exalt.MultiThreading.Repository;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface CustomerRepository extends AerospikeRepository<CustomerDao, String> {

}
