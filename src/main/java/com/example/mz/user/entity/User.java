package com.example.mz.user.entity;

import com.example.mz.career.entity.Career;
import com.example.mz.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<Career> career;

    public static User create(final UserRequestDto.Create request){
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .school(request.getSchool())
                .semester(request.getSemester())
                .enroll(request.getEnroll())
                .graduate(request.isGraduate())
                .age(request.getAge())
                .grade(request.getGrade())
                .major(request.getMajor())
                .path(request.getPath())
                .military(request.isMilitary())
                .build();
    }

    public static User update(final UserRequestDto.Update request){
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .school(request.getSchool())
                .semester(request.getSemester())
                .enroll(request.getEnroll())
                .graduate(request.getGraduate())
                .age(request.getAge())
                .grade(request.getGrade())
                .major(request.getMajor())
                .path(request.getPath())
                .military(request.getMilitary())
                .build();
    }

    public static UserRequestDto.Read read(final User user){
        return UserRequestDto.Read.builder()
                .name(user.getName())
                .school(user.getSchool())
                .semester(user.getSemester())
                .enroll(user.getEnroll())
                .graduate(user.getGraduate())
                .major(user.getMajor())
                .path(user.getPath())
                .careers(user.getCareer())
                .build();
    }
}
