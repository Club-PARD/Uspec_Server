package com.example.mz.sigunUp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SignUpResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PathRequest{
        private String path;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class PathRet{
        private List<String> pathCategory;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class PathReq{
        private String path;
        private List<String> pathCategory;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SchoolReq{
        private String school;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SchoolRet{
        private String school;
        private String region;
    }
}
