package com.Exalt.MultiThreading.Domain.Mapper;

import com.Exalt.MultiThreading.Domain.Constants;
import com.Exalt.MultiThreading.Domain.Dao.ServerDao;
import com.Exalt.MultiThreading.Domain.Domain.Server;
import com.Exalt.MultiThreading.Application.Dto.ServerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerMapper {

    @Autowired
    ModelMapper modelMapper;

    public ServerDao serverDtoToDao(ServerDto serverDto) {
        ServerDao serverDao = modelMapper.map(serverDto, ServerDao.class);
        serverDao.setRemainingCap(serverDto.getRemainingCapacity());
        serverDao.setTotalCap(Constants.ServerMaximumCapacity);
        return serverDao;
    }

    public ServerDao serverDomToDao(Server serverDom) {
        ServerDao serverDao = modelMapper.map(serverDom, ServerDao.class);
        serverDao.setRemainingCap(serverDom.getRemainingCapacity());
        serverDao.setTotalCap(Constants.ServerMaximumCapacity);
        return serverDao;
    }

    public ServerDto serverDaoToDto(ServerDao serverDao) {
        ServerDto serverDto = modelMapper.map(serverDao, ServerDto.class);
        serverDto.setRemainingCapacity(serverDao.getRemainingCap());
        return serverDto;
    }

    public Server serverDtoToDom(ServerDto serverDto) {
        return modelMapper.map(serverDto, Server.class);
    }

    public ServerDto serverDomToDto(Server serverDom) {
        return modelMapper.map(serverDom, ServerDto.class);
    }

    public Server serverDaoToDom(ServerDao serverDoa) {
        Server serverDom = modelMapper.map(serverDoa, Server.class);
        serverDom.setRemainingCapacity(serverDoa.getRemainingCap());
        return serverDom;
    }
}
