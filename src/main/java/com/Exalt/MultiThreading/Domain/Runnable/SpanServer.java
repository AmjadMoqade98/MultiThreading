package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Dom.ServerProvider;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class SpanServer implements Runnable {

    ServerProvider serverProvider;
    ServerRepository serverRepository;
    ServerMapper serverMapper;
    ServerDom serverDom;

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

    public void setServerDom(ServerDom serverDom) {
        this.serverDom = serverDom;
    }
}
