package com.example.mz.career.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class CareerRequestDto {

    @Getter
    @Setter
    @Schema(description = "이력 생성할 때 백엔드로 주는 값들")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateCareer{
        private String type;
        private String careerName;
        private List<String> categories;
        private LocalDate start;
        private LocalDate end;
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read{
        private Long careerId;
        private String type;
        private String careerName;
        private List<String> categories;
        private LocalDate start;
        private LocalDate end;
    }
}
