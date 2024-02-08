package com.example.mz.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {
    @GetMapping("/top3")
    public ResponseEntity<MockDto> top3Career(){
        MockDto mockDto = MockDto.builder()
                .firstRankCareer("대외활동")
                .secondRankCareer("교내활동")
                .thirdRankCareer("인턴")
                .firstRankPercentage(27.9)
                .seccondRankPercentage(24.1)
                .thirdRankPercentage(19.1)
                .build();
        return new ResponseEntity<>(mockDto, HttpStatus.OK);
    }

    @GetMapping("ranks")
    public ResponseEntity<MockDto> allRank(){
        MockDto mockDto = MockDto.builder()
                .firstRankCareer("대외활동")
                .secondRankCareer("교내활동")
                .thirdRankCareer("인턴")
                .fourthRankCareer("공모전")
                .fifthRankCareer("자격증")
                .firstRankPercentage(27.9)
                .seccondRankPercentage(24.1)
                .thirdRankPercentage(19.1)
                .fourthRankPercentage(15.5)
                .fifthRankPercentage(11.2)
                .build();
        return new ResponseEntity<>(mockDto, HttpStatus.OK);
    }
}
