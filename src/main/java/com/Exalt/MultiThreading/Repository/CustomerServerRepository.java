package com.Exalt.MultiThreading.Repository;

import com.Exalt.MultiThreading.Dao.CustomerServerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface CustomerServerRepository extends AerospikeRepository<CustomerServerDao, String> {

}