package com.example.mz.user.entity;

import com.example.mz.career.entity.Career;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<Career> career;
}
