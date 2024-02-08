package com.example.mz.career.dto;

import com.example.mz.category.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CareerRequestDto {

    @Getter
    @Setter
    @Schema(description = "이력 생성할 때 백엔드로 주는 값들")
    public static class Create{
        private String careerName;
        private List<Category> categories;
    }
}
