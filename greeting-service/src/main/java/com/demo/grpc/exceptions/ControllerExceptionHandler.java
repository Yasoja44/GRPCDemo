package com.demo.grpc.exceptions;

import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;




@GrpcAdvice
public class ControllerExceptionHandler {

    @GrpcExceptionHandler
    public Status handleInvalidArgument(Exception e) {
        return Status.PERMISSION_DENIED.withDescription("Error!!!").withCause(e);
    }

    @GrpcExceptionHandler(YesException.class)
    public StatusException handleResourceNotFoundException(YesException e) {
        Status status = Status.INVALID_ARGUMENT.withDescription("Yes Entered!!!").withCause(e);






        Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

        Metadata extraHeaders = new Metadata();
        extraHeaders.put(AUTHORIZATION_HEADER, "Bearer" );
        MetadataUtils.newAttachHeadersInterceptor(extraHeaders);

//        return  MetadataUtils.newAttachHeadersInterceptor(extraHeaders);
        System.out.println(extraHeaders);
        return status.asException(extraHeaders);
    }

    @GrpcExceptionHandler
    public StatusException handleArithmeticException(ArithmeticException e) {
        Status status = Status.ABORTED.withDescription("Divided by Zero!!!").withCause(e);
        Metadata metadata = new Metadata();
        return status.asException(metadata);
    }


}
