package com.example.mz.career.entity;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.global.converter.ArrayConverter;
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


    public static Career create(final @NotNull CareerRequestDto.Create request){
        return Career.builder()
                .careerName(request.getCareerName())
                .type(request.getType())
                .categories(request.getCategories())
                .start(request.getStart())
                .end(request.getEnd())
                .build();
    }
}
