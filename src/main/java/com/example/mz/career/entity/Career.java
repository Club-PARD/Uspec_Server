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
    @Convert(converter = ArrayConverter.class)
    private List<String> categories;
    private LocalDate start;
    private LocalDate end;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public static Career create(final @NotNull CareerRequestDto.CreateCareer request){
        return Career.builder()
                .careerName(request.getCareerName())
                .type(request.getType())
                .categories(request.getCategories())
                .start(request.getStart())
                .end(request.getEnd())
                .build();
    }
}
