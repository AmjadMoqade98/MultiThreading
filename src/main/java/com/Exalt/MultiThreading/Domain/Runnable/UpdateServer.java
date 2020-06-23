package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Dto.ServerDto;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Domain.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpdateServer implements Runnable{

    @Autowired
    ServerService serverService ;

    @Autowired
    ServerMapper serverMapper ;

    ServerDom serverDom ;

    @Override
    public void run() {
        while (!serverDom.isActive())
            serverService.updateServer(serverMapper.serverDomToDto(serverDom));
    }

    public void setServerDom(ServerDom serverDom) {
        this.serverDom = serverDom;
    }
}
