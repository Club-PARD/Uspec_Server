package com.example.mz.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {
    @Getter
    @Builder
    public static class CreateRet{
        private Long id;
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
}
