package com.example.mz.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    //    예외처리하고 싶은 에러들 정의
    USERID_NOT_FOUND(HttpStatus.BAD_REQUEST,"userID에 맞는 유저정보가 없습니다"),
    FILE_TRANSFORM_FAILED(HttpStatus.BAD_REQUEST,"파일 변환에 실패했습니다"),
    PATH_NOT_FOUNT(HttpStatus.BAD_REQUEST,"경로에 맞는 정보가 없습니다"),
    SCHOOL_NOT_FOUND(HttpStatus.BAD_REQUEST,"검색하신 정보에 맞는 학교가 조회되지 않습니다");

    private HttpStatus status;
    private String message;
}
