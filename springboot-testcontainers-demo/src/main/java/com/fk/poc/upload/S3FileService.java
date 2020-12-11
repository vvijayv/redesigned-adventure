package com.fk.poc.upload;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class S3FileService {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    AmazonProperties amazonProperties;

    public String upload(String file, byte[] content) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.length);
        amazonS3.putObject(amazonProperties.getS3().getBucket(), amazonProperties.getS3().getKey() + file,
                new ByteArrayInputStream(content), metadata);
        return file;
    }

    public List<String> listFiles() {
        ObjectListing listObjects = amazonS3.listObjects(amazonProperties.getS3().getBucket(),
                amazonProperties.getS3().getKey());
        return listObjects.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }
}
