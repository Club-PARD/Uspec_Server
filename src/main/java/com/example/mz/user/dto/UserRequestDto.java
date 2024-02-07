package com.example.mz.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {
    @Getter
    @Setter
    @Builder
    public static class Create{
        private Long id;
        private String name;
        private String email;
        private String school;
        private Long semester;
        private String enroll;
        private boolean graduate;
        private Long age;
        private float grade;
        private String major;
        private String path;
        private boolean military;
    }
}
