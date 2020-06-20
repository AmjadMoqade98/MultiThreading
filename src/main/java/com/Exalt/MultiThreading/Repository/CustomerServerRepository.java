package com.Exalt.MultiThreading.Repository;

import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface CustomerServerRepository extends AerospikeRepository<CustomerServerRepository, String> {

}
