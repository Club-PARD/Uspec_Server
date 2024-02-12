package com.example.mz.global.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.mz.global.exception.CustomException;
import com.example.mz.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile).orElseThrow(() -> new CustomException(ExceptionCode.FILE_TRANSFORM_FAILED));

        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
//        public Read권한으로 업로드한다
        amazonS3Client.putObject(bucket, fileName, uploadFile);
//        File의 url 을 리턴한다
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다");
        } else {
            log.info("파일이 삭제되지 못했습니다");
        }
    }

    public Optional<File> convert(MultipartFile file) throws IOException {
//        기존 파일이름으로 새로운 파일을 생성, 이 객체는 프로그램의 루트 디렉토리에 생성된다
        File convertFile = new File(file.getOriginalFilename());
//        해당 경로에 파일이 없으면 새로 생성
        if (convertFile.createNewFile()) { // 해당 경로에 파일이 없을 경우, 새 파일 생성

            try (FileOutputStream fos = new FileOutputStream(convertFile)) {

                // multipartFile의 내용을 byte로 가져와서 write
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
//        새파일 생성 실패하면 빈 Optional 객체를 반환
        return Optional.empty();
    }
}
