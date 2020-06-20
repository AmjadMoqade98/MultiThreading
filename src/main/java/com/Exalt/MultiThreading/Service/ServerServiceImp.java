package com.Exalt.MultiThreading.Service;

import com.Exalt.MultiThreading.Dao.ServerDao;
import com.Exalt.MultiThreading.Dto.ServerDto;
import com.Exalt.MultiThreading.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Repository.ServerRepository;
import com.Exalt.MultiThreading.Dom.ServerDomainService;
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
    ServerDomainService serverDomainService;

    @Autowired
    FriendlyId friendlyId;

    public List<ServerDto> getServers() {
        List<ServerDao> serverDaos = new ArrayList<>();
        serverRepository.findAll().forEach(serverDaos::add);

        List<ServerDto> serverDtos = new ArrayList<>();
        for (ServerDao serverDao : serverDaos) {
            serverDtos.add(serverMapper.serverDaoToDto(serverDao));
            serverDomainService.addServerLocal(serverMapper.serverDaoToDom(serverDao));
        }
        return serverDtos;
    }

    public ServerDto getServer(String id) {
        return serverMapper.serverDaoToDto(serverRepository.findOne(id));
    }

    public void addServer(ServerDto serverDto) {
        if (serverDto.getId() == null) {
            serverDto.setId(friendlyId.createFriendlyId());
            serverDomainService.addServerLocal(serverMapper.serverDtoToDom(serverDto));
        }
        ServerDao serverDao = serverMapper.serverDtoToDao(serverDto);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverDomainService.activateServerLocal(serverDto.getId());
        serverRepository.save(serverDao);
    }

    public void updateServer(ServerDto serverDto) {
        serverRepository.save(serverMapper.serverDtoToDao(serverDto));
        serverDomainService.updateServerLocal(serverMapper.serverDtoToDom(serverDto));
    }

    public void deleteServer(String id) {
        serverRepository.delete(id);
        serverDomainService.deleteServerLocal(id);
    }

    public void deleteServers() {
        serverRepository.deleteAll();
        serverDomainService.clearServersLocal();
    }

}
