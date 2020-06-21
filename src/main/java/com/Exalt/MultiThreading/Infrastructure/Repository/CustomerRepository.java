package com.Exalt.MultiThreading.Infrastructure.Repository;

import com.Exalt.MultiThreading.Domain.Dao.CustomerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface CustomerRepository extends AerospikeRepository<CustomerDao, String> {

}
