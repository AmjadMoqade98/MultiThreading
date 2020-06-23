package com.Exalt.MultiThreading.Domain.Runnable;

import com.Exalt.MultiThreading.Domain.Dom.ServerDom;
import com.Exalt.MultiThreading.Domain.Dom.ServerProvider;
import com.Exalt.MultiThreading.Domain.Dto.ServerDto;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Domain.Service.ServerService;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
