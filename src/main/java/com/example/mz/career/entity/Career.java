package com.example.mz.career.entity;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.category.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private String careerName;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "careerId")
    private List<Category> categories;

    public static Career create(final @NotNull CareerRequestDto.Create request){
        return Career.builder()
                .careerName(request.getCareerName())
                .categories(request.getCategories())
                .build();
    }
}
