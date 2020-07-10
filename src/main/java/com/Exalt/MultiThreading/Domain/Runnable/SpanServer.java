package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Domain.Server;
import com.Exalt.MultiThreading.Domain.Service.ServerProvider;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Domain.Repository.ServerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpanServer implements Runnable {

    ServerProvider serverProvider;
    ServerRepository serverRepository;
    ServerMapper serverMapper;
    Server serverDom;

    @Override
    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        serverProvider.activateServerLocal(serverDom.getId());
        serverRepository.save(serverMapper.serverDomToDao(serverDom));
    }

    public void setServerDom(Server serverDom) {
        this.serverDom = serverDom;
    }
}
