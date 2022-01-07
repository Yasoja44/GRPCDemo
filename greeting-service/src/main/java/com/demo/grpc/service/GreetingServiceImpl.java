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
        System.out.println("Received Message: " + message);

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

//@GrpcAdvice
//class ExceptionHandlers {
//    @GrpcExceptionHandler(NullPointerException.class)
//    public static Status serviceException(NullPointerException exception) {
//        return Status.INVALID_ARGUMENT.withDescription("Your description").withCause(exception);
//        //System.out.println("Here " + exception);
//    }
//}
