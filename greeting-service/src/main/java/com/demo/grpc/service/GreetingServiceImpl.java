package com.demo.grpc.service;

import com.demo.grpc.GreetingServiceGrpc;
import com.demo.grpc.GreetingRequest;
import com.demo.grpc.GreetingResponse;
import com.demo.grpc.exceptions.YesException;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver)  {

        String message = request.getMessage();

        if(message.equals("yes")){
            throw new YesException();
        } else {

            try{
                int no = Integer.parseInt(message);
                double divided = (1000/no) * 1.0;

                GreetingResponse greetingResponse = GreetingResponse.newBuilder()
                        .setMessage("Received: " + divided)
                        .build();

                responseObserver.onNext(greetingResponse);
                responseObserver.onCompleted();
            }catch (NumberFormatException e){
                throw new NumberFormatException();
            }
        }
    }
}

