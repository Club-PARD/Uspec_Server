package com.example.mz.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserResponseDto {
    @Getter
    @Builder
    public static class CreateRet{
        private Long id;
    }

    @Getter
    @Builder
    public static class PathRet{
        private List<String> pathCategory;
    }

    @Getter
    @Builder
    public static class ImageRet{
        private String imageUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CareerInUser{
        private String careerName;
        private String type;
        private String category;
        private String start;
        private String end;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserCareerInfo{
        private String name;
        private String enroll;
        private Long careerCount;
    }
}
