package com.example.mz.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {
    @Getter
    @Builder
    public static class CreateRet{
        private Long id;
    }
}
