package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Application.Dto.ServerDto;

import java.util.List;

public interface ServerService {

    public List<ServerDto> getServers();

    public ServerDto getServer(String id);

    public boolean rentServer(String id, int space);

    public void deleteServer(String id);

    public void deleteServers();
}
