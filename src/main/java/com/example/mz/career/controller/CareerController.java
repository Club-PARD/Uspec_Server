package com.example.mz.career.controller;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.service.CareerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/career")
public class CareerController {
    private final CareerService careerService;

    @PostMapping("")
    @Operation(description = "이력 등록하는 controller. " +
            "대외활동, 공모전, 인턴, 자격증, 교내활동 중 하나를 careerName으로, 카테고리들을 List<String>으로 저장합니다")
    public ResponseEntity<?> add(@RequestBody CareerRequestDto.Create careerRequestDto){
        careerService.addCareer(careerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
