package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Application.Dto.ServerDto;
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
    ServerProvider serverProvider;

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


    public void deleteServer(String id) {
        serverRepository.delete(id);
        serverProvider.deleteServerLocal(id);
    }

    public void deleteServers() {
        serverRepository.deleteAll();
        serverProvider.clearServersLocal();
    }

    public boolean rentServer(String id, int space) {
        if(customerValidation.validateCustomerId(id) == false) {
            return false ;
        }
        if (rentValidation.validateRentSpace(space) == false) {
            return false;
        }
        serverProvider.RentServer(id, space);
        return true;
    }
}
