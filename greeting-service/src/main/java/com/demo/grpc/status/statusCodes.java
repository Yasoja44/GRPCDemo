package com.demo.grpc.status;

import io.grpc.Status;

public class statusCodes {;

    public static final int OK = 200;
    public static final int INVALID_ARGUMENT = 400;
    public static final int FAILED_PRECONDITION = 400;
    public static final int OUT_OF_RANGE = 400;
    public static final int UNAUTHENTICATED = 401;
    public static final int PERMISSION_DENIED = 403;
    public static final int NOT_FOUND = 404;
    public static final int ABORTED = 409;
    public static final int ALREADY_EXISTS = 409;
    public static final int RESOURCE_EXHAUSTED = 429;
    public static final int CANCELLED = 499;
    public static final int DATA_LOSS = 500;
    public static final int UNKNOWN = 500;
    public static final int INTERNAL = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int UNAVAILABLE = 503;
    public static final int DEADLINE_EXCEEDED = 504;

    public int getHttpStatus(Status.Code code){
        if(code.value() == 0 ){
            return OK;
        }else if(code.value() == 1){
            return CANCELLED;
        }else if(code.value() == 2){
            return UNKNOWN;
        }else if(code.value() == 3){
            return INVALID_ARGUMENT;
        }else if(code.value() == 4){
            return DEADLINE_EXCEEDED;
        }else if(code.value() == 5){
            return NOT_FOUND;
        }else if(code.value() == 6){
            return ALREADY_EXISTS;
        }else if(code.value() == 7){
            return PERMISSION_DENIED;
        }else if(code.value() == 8){
            return RESOURCE_EXHAUSTED;
        }else if(code.value() == 9){
            return FAILED_PRECONDITION;
        }else if(code.value() == 10){
            return ABORTED;
        }else if(code.value() == 11){
            return OUT_OF_RANGE;
        }else if(code.value() == 12){
            return NOT_IMPLEMENTED;
        }else if(code.value() == 13){
            return INTERNAL;
        }else if(code.value() == 14){
            return UNAVAILABLE;
        }else if(code.value() == 15){
            return DATA_LOSS;
        }else{
            return 0;
        }

    }

}
