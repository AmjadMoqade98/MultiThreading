package com.Exalt.MultiThreading.Domain.Repository;

import com.Exalt.MultiThreading.Domain.Dao.ServerDao;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface ServerRepository extends AerospikeRepository<ServerDao, String> {

}
