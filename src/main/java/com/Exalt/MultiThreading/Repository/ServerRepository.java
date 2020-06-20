package com.Exalt.MultiThreading.Repository;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import com.Exalt.MultiThreading.Dao.ServerDao;
import org.springframework.stereotype.Repository;
import org.springframework.data.aerospike.repository.AerospikeRepository;

@Repository
public interface ServerRepository extends AerospikeRepository<ServerDao, String> {
    ServerDao findOne(int id);

    ServerDao save(ServerDao serverDao);

    void delete(int id);

    void deleteAll();

    Iterable<ServerDao> findAll();
}
