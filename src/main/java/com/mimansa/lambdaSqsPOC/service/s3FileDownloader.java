package com.mimansa.lambdaSqsPOC.service;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Service;
import com.mimansa.lambdaSqsPOC.config.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class s3FileDownloader {

    AWSConfig awsConfig = new AWSConfig();



    public S3Object getFileName(S3Event input){

        //get file name
        S3Object s3Object = new S3Object();
        for(S3EventNotification.S3EventNotificationRecord record:input.getRecords()){
            String bucketName = record.getS3().getBucket().getName();
             String fileName = record.getS3().getObject().getKey();
            AmazonS3 s3Client = awsConfig.buildClient();
             s3Object = s3Client.getObject(bucketName,fileName);
        }
        return s3Object;
    }

    public void fileReader(S3Object s3Object) throws IOException {

        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        //print content
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
            br.lines().forEach(System.out::println);
        } finally {
            br.close();
        }


    }


}
