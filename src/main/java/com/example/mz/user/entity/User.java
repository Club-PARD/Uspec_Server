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
//    회원가입만 하면 ROLE_USER, 이력 작성하면 ROLE_CAREER
    private String role;
    @Column(columnDefinition = "BLOB",length = 1000)
    private String image;

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
                .role("ROLE_USER")
                .image(request.getImage())
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
                .image(request.getImage())
                .build();
    }

    public static UserRequestDto.Read read(final User user){
        return UserRequestDto.Read.builder()
                .name(user.getName())
                .role(user.getRole())
                .school(user.getSchool())
                .semester(user.getSemester())
                .enroll(user.getEnroll())
                .graduate(user.getGraduate())
                .major(user.getMajor())
                .path(user.getPath())
                .careers(user.getCareer())
                .image(user.getImage())
                .build();
    }
}
