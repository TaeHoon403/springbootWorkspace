package com.kh.app11;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("test")
    public String m01(MultipartFile f) throws IOException {
        
        // 파일명 변수 저장
        String originName = f.getOriginalFilename();

        // S3에 파일 업로드
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(f.getContentType());
        metaData.setContentLength(f.getSize());

        s3.putObject(bucket, originName,f.getInputStream(),metaData);

        // S3에 업로드 된 파일 URL
        String url = s3.getUrl(bucket,originName).toString();

        return url;
    }

}
