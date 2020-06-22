package com.Exalt.MultiThreading.Domain.Repository;

import com.Exalt.MultiThreading.Domain.Dao.ServerDao;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerRepositoryImp {

    @Autowired
    ServerRepository serverRepository;

    public ServerDao findOne(String id) {
        return serverRepository.findOne(id);
    }

    public ServerDao save(ServerDao serverDao) {
        return serverRepository.save(serverDao);
    }

    public void delete(String id) {
        serverRepository.delete(id);
    }

    public void deleteAll() {
        serverRepository.deleteAll();
    }

    public Iterable<ServerDao> findAll() {
        return serverRepository.findAll();
    }
}
