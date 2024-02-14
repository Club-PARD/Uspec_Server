package com.example.mz.career.entity;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.global.converter.ArrayConverter;
import com.example.mz.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "user")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long careerId;
    private String type;
    private String careerName;
//    @Convert(converter = ArrayConverter.class)
//    private List<String> categories;
    @ElementCollection
    @CollectionTable(name = "career_categories", joinColumns = @JoinColumn(name = "careerId"))
    @Column(name = "category")
    private List<String> categories;

    @ElementCollection
    @CollectionTable(name = "interests", joinColumns = @JoinColumn(name = "careerId"))
    @Column(name = "interest")
    private List<String> interests;

    private LocalDate start;
    private LocalDate end;
    private int month;
    private String monthForSelect;
    private String scale;
    private String jobRole;
    private LocalDate earnDate;
    @Column(columnDefinition = "TEXT",length = 1000)
    private String details;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public static Career create(final @NotNull CareerRequestDto.CreateCareer request){
        return Career.builder()
                .careerName(request.getCareerName())
                .type(request.getType())
                .categories(request.getCategories())
                .interests(request.getInterests())
                .start(request.getStart())
                .end(request.getEnd())
                .month(request.getMonth())
                .monthForSelect(request.getMonthForSelect())
                .scale(request.getScale())
                .jobRole(request.getJobRole())
                .earnDate(request.getEarnDate())
                .details(request.getDetails())
                .build();
    }
}
