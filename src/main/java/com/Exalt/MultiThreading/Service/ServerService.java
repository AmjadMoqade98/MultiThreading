package com.Exalt.MultiThreading.Service;

import com.Exalt.MultiThreading.Dto.ServerDto;

import java.util.List;

public interface ServerService {

    public List<ServerDto> getServers();

    public ServerDto getServer(String id);

    public void updateServer(ServerDto serverDto);

    public void addServer(ServerDto serverDto);

    public void deleteServer(String id);

    public void deleteServers();

}
