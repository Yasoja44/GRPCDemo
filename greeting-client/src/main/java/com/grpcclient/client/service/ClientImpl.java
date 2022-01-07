package com.grpcclient.client.service;

import com.demo.grpc.GreetingRequest;
import com.demo.grpc.GreetingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.stereotype.Service;

@Service
public class ClientImpl {

        @GrpcClient("greetingService")
        private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;

        public String receiveGreeting(String message) {
            GreetingRequest request = GreetingRequest.newBuilder()
                    .setMessage(message)
                    .build();
            return greetingServiceStub.greeting(request).getMessage();
        }


}
