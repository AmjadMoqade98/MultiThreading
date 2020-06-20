package com.Exalt.MultiThreading.Controller;

import com.Exalt.MultiThreading.Dto.ServerDto;
import com.Exalt.MultiThreading.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("server")
public class ServerController {

    @Autowired
    ServerService serverService;

    @GetMapping
    public List<ServerDto> getServers() {
        return serverService.getServers();
    }

    @GetMapping("/{id}")
    public ServerDto getServer(@PathVariable("id") String id) {
        return serverService.getServer(id);
    }

    @PostMapping
    public void addServer(@RequestBody final ServerDto serverDto) {
        serverService.addServer(serverDto);
    }

    @PutMapping("/{id}")
    public void updateServer(@RequestBody final ServerDto serverDto , @PathVariable("id") String id) {
        serverDto.setId(id);
        serverService.updateServer(serverDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServer(@PathVariable("id") String id) {
        serverService.deleteServer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteServers() {
        serverService.deleteServers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
