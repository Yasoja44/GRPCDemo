package com.grpcclient.client.service;

import com.demo.grpc.GreetingRequest;
import com.demo.grpc.GreetingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientImpl {

        @GrpcClient("greeting-service")
        private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;

        public Object receiveGreeting(String message) {

            try{

                GreetingRequest request = GreetingRequest.newBuilder()
                        .setMessage(message)
                        .build();

                return greetingServiceStub.greeting(request).getMessage();

            }catch (Exception e){
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }



        }




}
