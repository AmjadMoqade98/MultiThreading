package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Domain.Dao.ServerDao;
import com.Exalt.MultiThreading.Domain.Dto.ServerDto;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Infrastructure.Repository.ServerRepository;
import com.Exalt.MultiThreading.Domain.Dom.ServerProvider;
import com.Exalt.MultiThreading.Domain.validation.CustomerValidation;
import com.Exalt.MultiThreading.Domain.validation.RentValidation;
import com.Exalt.MultiThreading.Domain.validation.ServerValidation;
import com.devskiller.friendly_id.FriendlyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImp implements ServerService {

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    ServerMapper serverMapper;

    @Autowired
    ServerProvider serverDomainService;

    @Autowired
    FriendlyId friendlyId;

    @Autowired
    ServerValidation serverValidation;

    @Autowired
    CustomerValidation customerValidation;

    @Autowired
    RentValidation rentValidation ;

    public List<ServerDto> getServers() {
        List<ServerDto> serverDtos = new ArrayList<>();
        serverRepository.findAll().forEach(serverDao-> {
            serverDtos.add(serverMapper.serverDaoToDto(serverDao));
        });
        return serverDtos;
    }

    public ServerDto getServer(String id) {
        if (serverValidation.validateServerId(id) == false) {
            return null;
        }
        return serverMapper.serverDaoToDto(serverRepository.findOne(id));
    }

    public ServerDto addServer(ServerDto serverDto) {
        if (serverDto.getId() == null) {
            serverDto.setId(friendlyId.createFriendlyId());
            serverDomainService.addServerLocal(serverMapper.serverDtoToDom(serverDto));
        }
        ServerDao serverDao = serverMapper.serverDtoToDao(serverDto);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverDomainService.activateServerLocal(serverDto.getId());
        serverRepository.save(serverDao);
        return serverDto;
    }

    public ServerDto updateServer(ServerDto serverDto) {
        ServerDao serverDao = serverRepository.save(serverMapper.serverDtoToDao(serverDto));
        serverDomainService.updateServerLocal(serverMapper.serverDtoToDom(serverDto));
        return serverMapper.serverDaoToDto(serverDao);
    }

    public void deleteServer(String id) {
        serverRepository.delete(id);
        serverDomainService.deleteServerLocal(id);
    }

    public void deleteServers() {
        serverRepository.deleteAll();
        serverDomainService.clearServersLocal();
    }

    public boolean rentSpace(String id, int space) {
        if(customerValidation.validateCustomerId(id) == false) {
            return false ;
        }
        if (rentValidation.validateRentSpace(space) == false) {
            return false;
        }
        serverDomainService.RentServer(id, space);
        return true;
    }
}
