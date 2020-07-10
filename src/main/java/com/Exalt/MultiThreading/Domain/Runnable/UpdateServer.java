package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Domain.Server;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Domain.Repository.ServerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateServer implements Runnable{

    ServerRepository serverRepository;
    ServerMapper serverMapper;
    Server serverDom;

    @Override
    public void run() {
        while (!serverDom.isActive()){}
        serverRepository.save(serverMapper.serverDomToDao(serverDom));
    }

}
