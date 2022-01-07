package com.grpcclient.client.config;

import com.grpcclient.client.service.ClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private ClientImpl grpcClientService;

    @GetMapping("/{name}")
    public String printMessage(@PathVariable("name") String name) {
        return grpcClientService.receiveGreeting(name);
    }
}
