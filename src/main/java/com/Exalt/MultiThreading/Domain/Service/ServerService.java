package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Domain.Dto.ServerDto;

import java.util.List;

public interface ServerService {

    public List<ServerDto> getServers();

    public ServerDto getServer(String id);

    public ServerDto updateServer(ServerDto serverDto);

    public ServerDto addServer(ServerDto serverDto);

    public void deleteServer(String id);

    public void deleteServers();

    public boolean rentSpace(String customerId, int space);
}
