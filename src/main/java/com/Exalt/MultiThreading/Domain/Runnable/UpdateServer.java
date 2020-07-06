package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateServer implements Runnable{

    ServerRepository serverRepository;
    ServerMapper serverMapper;
    ServerDom serverDom;

    @Override
    public void run() {
        while (!serverDom.isActive()){}
        serverRepository.save(serverMapper.serverDomToDao(serverDom));
    }

}
