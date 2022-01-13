package com.grpcclient.client.service;

import com.demo.grpc.GreetingRequest;
import com.demo.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientImpl {

    @GrpcClient("greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;
    private StreamObserver<GreetingRequest> requestObserver;


    private void sendRequest(String message) {
        requestObserver.onNext(GreetingRequest.newBuilder().setMessage(message).build());
    }

//    public Object receiveGreeting(String message) {
//        greetingServiceStub.greeting(new StreamObserver<GreetingResponse> (){
//            @Override
//            public void onNext(GreetingResponse response) {
//               sendRequest("77");
//                // Optionally you can call onCompleted() or onError() on
//                // the requestObserver to terminate the call.
//
//            }
//
//            @Override
//            public void onCompleted() {
//                // You should not call any method on requestObserver.
//
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                // You should not call any method on requestObserver.
//            }
//        });
//        return null;
//    }




    public Object receiveGreeting(String message) {

        final String[] res = new String[1];
        final ResponseEntity[] res2 = new ResponseEntity[1];


//        return greetingServiceStub.greeting(request).getMessage();

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
                System.out.println("asdasd "+throwable);
                res[0] = throwable.toString();

                if(throwable.getMessage().contains("INVALID_ARGUMENT")){
                    res2[0] = new ResponseEntity(throwable.getMessage(), HttpStatus.NOT_ACCEPTABLE);
                }else if(throwable.getMessage().contains("ABORTED")){
                    res2[0] = new ResponseEntity(throwable.getMessage(),HttpStatus.BAD_REQUEST);
                }else{
                    res2[0] = new ResponseEntity(throwable.getMessage(),HttpStatus.FORBIDDEN);
                }
            }

            @Override
            public void onCompleted() {
                GreetingRequest request = GreetingRequest.newBuilder()
                        .setMessage(message)
                        .build();

                //System.out.println("asdasd "+greetingServiceStub.greeting(request).getMessage());
            }
        };

        try {

            streamObserver.onNext(message);
            return res[0];


        } catch (RuntimeException e) {
            streamObserver.onError(e);
            return res2[0];
            //throw e;
        }
//        streamObserver.onCompleted();



        }






//    public Object receiveGreeting(String message) {
//
//            try{
//
//                GreetingRequest request = GreetingRequest.newBuilder()
//                        .setMessage(message)
//                        .build();
//
//                return greetingServiceStub.greeting(request).getMessage();
//
//            }catch (Exception e){
//                if(e.getMessage().contains("INVALID_ARGUMENT")){
//                    return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
//                }else if(e.getMessage().contains("ABORTED")){
//                    return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
//                }else{
//                    return new ResponseEntity(e.getMessage(),HttpStatus.FORBIDDEN);
//                }
//
//            }
//
//        }

}
