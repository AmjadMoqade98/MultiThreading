package com.Exalt.MultiThreading.Mapper;

import com.Exalt.MultiThreading.Constants;
import com.Exalt.MultiThreading.Dao.ServerDao;
import com.Exalt.MultiThreading.Dom.ServerDom;
import com.Exalt.MultiThreading.Dto.ServerDto;
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

    public ServerDto serverDaoToDto(ServerDao serverDao) {
        ServerDto serverDto = modelMapper.map(serverDao, ServerDto.class);
        serverDto.setRemainingCapacity(serverDao.getRemainingCap());
        return serverDto;
    }

    public ServerDom serverDtoToDom(ServerDto serverDto) {
        return modelMapper.map(serverDto, ServerDom.class);
    }

    public ServerDto serverDomToDto(ServerDom serverDom) {
        return modelMapper.map(serverDom, ServerDto.class);
    }

    public ServerDom serverDaoToDom(ServerDao serverDoa) {
        ServerDom serverDom = modelMapper.map(serverDoa, ServerDom.class);
        serverDom.setRemainingCapacity(serverDoa.getRemainingCap());
        return serverDom;
    }
}
