package com.example.mz.user.dto;

import com.example.mz.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {
    @Getter
    @Setter
    @Builder
    public static class Create{
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
    @Getter
    @Setter
    @Builder
    public static class Update{
        private String name;
        private String email;
        private String school;
        private Long semester;
        private String enroll;
        private Boolean graduate;
        private Long age;
        private float grade;
        private String major;
        private String path;
        private Boolean military;
        public void updateEntity(User user) {
            if (name != null) user.setName(this.name);
            if (email != null) user.setEmail(this.email);
            if (school != null) user.setSchool(this.school);
            if (semester != null) user.setSemester(this.semester);
            if (enroll != null) user.setEnroll(this.enroll);
            if(graduate != null) user.setGraduate(this.graduate);
            if (age != null) user.setAge(this.age);
            if (grade != 0) user.setGrade(this.grade);
            if (major != null) user.setMajor(this.major);
            if (path != null) user.setPath(this.path);
            if(military != null ) user.setMilitary(this.military);
        }
    }
}
