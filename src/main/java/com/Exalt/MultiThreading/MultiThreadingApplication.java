package com.Exalt.MultiThreading;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Dom.ServerProvider;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class MultiThreadingApplication implements CommandLineRunner {

    @Autowired
    ServerRepository serverRepository ;

    @Autowired
    ServerMapper serverMapper ;

    public static void main(String[] args) {
        SpringApplication.run(MultiThreadingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //caching servers locally
        serverRepository.findAll().forEach(serverDao -> {
            ServerDom serverDom = serverMapper.serverDaoToDom(serverDao);
            ServerProvider.serversCache.put(serverDom.getId(),serverDom);
        });
    }
}