package com.example.mz.career.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
        private List<String> interests;
        private LocalDate start;
        private LocalDate end;
        private int month;
        private String monthForSelect;
        private String scale;
        private String jobRole;
        private LocalDate earnDate;
        private String details;
    }

    @Getter
    @AllArgsConstructor
    public static class CareerSummary {
        private String type;
        private long count;
        private long percentage;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CareerNameSummary {
        private String type;
        private String category;
        Long count;
        private long meanPerCareer;
        String careerName;
        String monthForDisplay;
        String jobRole;

        // 대외활동, 교내활동, 공모전 정보를 위한 정적 팩토리 메서드
        public static CareerNameSummary forGeneralInfo(String type, String category, Long count, Long meanPerCareer) {
            CareerNameSummary summary = new CareerNameSummary();
            summary.type = type;
            summary.category = category;
            summary.count = count;
            summary.meanPerCareer = meanPerCareer;
            return summary;
        }

        // 인턴 정보를 위한 정적 팩토리 메서드
        public static CareerNameSummary forInternInfo(String type, String careerName, String monthForDisplay, Long meanPerCareer, String jobRole) {
            CareerNameSummary summary = new CareerNameSummary();
            summary.type = type;
            summary.careerName = careerName;
            summary.monthForDisplay = monthForDisplay;
            summary.meanPerCareer = meanPerCareer;
            summary.jobRole = jobRole;
            return summary;
        }

        // 자격증 정보를 위한 정적 팩토리 메서드
        public static CareerNameSummary forCertificationInfo(String careerName, Long count, Long meanPerCareer) {
            CareerNameSummary summary = new CareerNameSummary();
            summary.careerName = careerName;
            summary.count = count;
            summary.meanPerCareer = meanPerCareer;
            return summary;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class UserSpecRank{
        private String name;
        private String enroll;
        private Long careerNum;
    }

}
