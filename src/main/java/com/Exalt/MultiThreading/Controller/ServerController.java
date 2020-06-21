package com.Exalt.MultiThreading.Controller;

import com.Exalt.MultiThreading.Dto.ServerDto;
import com.Exalt.MultiThreading.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("server")
public class ServerController {

    @Autowired
    ServerService serverService;

    @GetMapping
    public ResponseEntity getServers() {
        return new ResponseEntity(serverService.getServers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getServer(@PathVariable("id") String id) {
        ServerDto serverDto = serverService.getServer(id);
        if (serverDto == null) {
            return new ResponseEntity("server does not exist", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(serverDto, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity addServer(@RequestBody final ServerDto serverDto) {
        return new ResponseEntity(serverService.addServer(serverDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateServer(@RequestBody final ServerDto serverDto, @PathVariable("id") String id) {
        serverDto.setId(id);
        return new ResponseEntity(serverService.updateServer(serverDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServer(@PathVariable("id") String id) {
        serverService.deleteServer(id);
        return new ResponseEntity<>("server deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteServers() {
        serverService.deleteServers();
        return new ResponseEntity<>("servers deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/rent")
    public ResponseEntity RentServer(@RequestParam("space") int space, @RequestParam("id") String id) {
        if (serverService.rentSpace(id, space) == true) {
            return new ResponseEntity<>("server rented successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid space or Invalid customerId", HttpStatus.OK);
        }
    }
}
