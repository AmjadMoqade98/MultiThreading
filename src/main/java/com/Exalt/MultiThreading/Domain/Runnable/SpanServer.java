package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Dto.ServerDto;
import com.Exalt.MultiThreading.Domain.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SpanServer implements Runnable{

    @Autowired
    ServerService serverService ;

    ServerDto serverDto ;

    @Override
    public void run() {
        serverService.addServer(serverDto);
    }

    public void setServerDto(ServerDto serverDto) {
        this.serverDto = serverDto;
    }
}
