package com.Exalt.MultiThreading.Infrastructure.Repository;

import com.Exalt.MultiThreading.Domain.Dao.ServerDao;
import org.springframework.stereotype.Repository;
import org.springframework.data.aerospike.repository.AerospikeRepository;

@Repository
public interface ServerRepository extends AerospikeRepository<ServerDao, String> {

}
