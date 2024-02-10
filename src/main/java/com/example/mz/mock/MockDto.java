package com.example.mz.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MockDto {

        private String firstRankCareer;
        private String secondRankCareer;
        private String thirdRankCareer;
        private String fourthRankCareer;
        private String fifthRankCareer;
        private Double firstRankPercentage;
        private Double seccondRankPercentage;
        private Double thirdRankPercentage;
        private Double fourthRankPercentage;
        private Double fifthRankPercentage;

}
