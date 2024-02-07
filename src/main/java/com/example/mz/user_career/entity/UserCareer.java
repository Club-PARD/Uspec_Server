package com.example.mz.user_career.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCareerId;
    private String userCareerName;
    private Timestamp start;
    private Timestamp finish;
}
