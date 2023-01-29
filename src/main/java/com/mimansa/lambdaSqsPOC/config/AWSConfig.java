package com.mimansa.lambdaSqsPOC.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.stereotype.Component;

@Component
public class AWSConfig {

    public AmazonS3 buildClient(){
        return AmazonS3Client.builder()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

}
