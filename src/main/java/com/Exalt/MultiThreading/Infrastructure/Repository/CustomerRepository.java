package com.Exalt.MultiThreading.Infrastructure.Repository;

import com.Exalt.MultiThreading.Domain.Dao.CustomerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerDao, String> {

}
