package com.demo.grpc.exceptions;


import com.demo.grpc.status.statusCodes;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;




@GrpcAdvice
public class ControllerExceptionHandler {

    statusCodes statuscodes = new statusCodes();

    @GrpcExceptionHandler
    public Status handleInvalidArgument(Exception e) {
        return Status.PERMISSION_DENIED.withDescription("Error!!!").withCause(e);
    }

    @GrpcExceptionHandler(YesException.class)
    public StatusException handleResourceNotFoundException(YesException e) {
        Status status = Status.INVALID_ARGUMENT.withDescription("Yes Entered!!!").withCause(e);

        Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.of("Authorization_Yes", Metadata.ASCII_STRING_MARSHALLER);

        Metadata extraHeaders = new Metadata();
        extraHeaders.put(AUTHORIZATION_HEADER, "Bearer_Yes" );
        MetadataUtils.newAttachHeadersInterceptor(extraHeaders);

//        return  MetadataUtils.newAttachHeadersInterceptor(extraHeaders);


        System.out.println(extraHeaders);
        return status.asException(extraHeaders);
    }

    @GrpcExceptionHandler(ArithmeticException.class)
    public StatusException handleArithmeticException(ArithmeticException e) {
        Status status = Status.ABORTED.withDescription("Divided by Zero!!!").withCause(e);
        Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.of("Authorization_Zero", Metadata.ASCII_STRING_MARSHALLER);
        Metadata.Key<String> AUTHORIZATION_HTTP = Metadata.Key.of("Authorization_Http", Metadata.ASCII_STRING_MARSHALLER);

        Status.Code code = status.getCode();
        System.out.println("code:" +code);


        int httpCode = statuscodes.getHttpStatus(code);

        Metadata extraHeaders2 = new Metadata();
        extraHeaders2.put(AUTHORIZATION_HEADER, "Bearer_Zero" );
        extraHeaders2.put(AUTHORIZATION_HTTP, Integer.toString(httpCode));

        MetadataUtils.newAttachHeadersInterceptor(extraHeaders2);

//        return  MetadataUtils.newAttachHeadersInterceptor(extraHeaders);

        return status.asException(extraHeaders2);
    }


}
