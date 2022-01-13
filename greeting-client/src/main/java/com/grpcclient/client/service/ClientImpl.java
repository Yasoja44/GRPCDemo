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
                if(e.getMessage().contains("INVALID_ARGUMENT")){
                    return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
                }else if(e.getMessage().contains("ABORTED")){
                    return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
                }else{
                    return new ResponseEntity(e.getMessage(),HttpStatus.FORBIDDEN);
                }

            }



        }




}
