package com.demo.grpc;

import com.demo.grpc.service.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcSpringBootExampleApplication {

	public static void main(String[] args)  {
		SpringApplication.run(GrpcSpringBootExampleApplication.class, args);


	}

}
