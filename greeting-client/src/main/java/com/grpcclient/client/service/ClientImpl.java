package com.grpcclient.client.service;

import com.demo.grpc.GreetingRequest;
import com.demo.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientImpl {

    @GrpcClient("greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;
    private StreamObserver<GreetingRequest> requestObserver;


    public Object receiveGreeting(String message) {

        final String[] res = new String[1];

        StreamObserver streamObserver = new StreamObserver() {
            @Override
            public void onNext(Object message) {

                GreetingRequest request = GreetingRequest.newBuilder()
                        .setMessage(message.toString())
                        .build();

                res[0] = greetingServiceStub.greeting(request).getMessage();
            }


            @Override
            public void onError(Throwable throwable) {
                res[0] = throwable.getMessage();
            }

            @Override
            public void onCompleted() {
                System.out.println("Done");
            }
        };

        try {
            streamObserver.onNext(message);
        }catch (RuntimeException e) {
            streamObserver.onError(e);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", String.valueOf(res[0]));

            return ResponseEntity.badRequest()
                    .headers(headers)
                    .body("Error");

        }

        streamObserver.onCompleted();
        return res[0];
        }

}
