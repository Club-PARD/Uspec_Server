package com.example.mz.career.controller;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.service.CareerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CareerController {
    private final CareerService careerService;

    @PostMapping("/{id}/career")
    @Operation(description = "이력 등록하는 controller. " +
            "대외활동, 공모전, 인턴, 자격증, 교내활동들을 type으로, 각 활동 이름을 careerName, 카테고리들을 List<String>으로 저장합니다")
    public ResponseEntity<?> add(Long id,@RequestBody CareerRequestDto.CreateCareer careerRequestDto){
        careerService.addCareer(id,careerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/type/top3")
    @Operation(description = "user의 분야에서 이력 상위 3개와 전체 대비 비율을 가져오는 컨트롤러")
    public ResponseEntity<List<CareerRequestDto.CareerSummary>> getTop3WithPercentage(@PathVariable Long id){
        List<CareerRequestDto.CareerSummary> topCareersWithPercentage = careerService.getTop3WithPercentage(id);
        return ResponseEntity.ok(topCareersWithPercentage);
    }

//    @GetMapping("/type/category/top3")
//    @Operation(description = "제일 높은 이력의 상위 카테고리 3래 가져오는 컨트롤러")
//    public ResponseEntity<List<CareerRequestDto.CareerNameSummary> > getTopTypeWithTop3CareerName(){
//        List<CareerRequestDto.CareerNameSummary> topTypeWithTop3CareerName = careerService.getTopTypeWithTop3CareerName();
//        return ResponseEntity.ok(topTypeWithTop3CareerName);
//    }

    @GetMapping("/{id}/rank/spec")
    @Operation(description = "이 분야 상위 3명 스펙 랭킹 보여주는 컨트롤러")
    public ResponseEntity<List<CareerRequestDto.UserSpecRank>> getTop3UserBySpecRank(@PathVariable Long id){
        List<CareerRequestDto.UserSpecRank> ret = careerService.getTop3UserBySpecRank(id);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

}
