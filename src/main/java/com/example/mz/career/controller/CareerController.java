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
public class CareerController {
    private final CareerService careerService;

    @PostMapping("/{id}/career")
    @Operation(description = "이력 등록하는 controller. " +
            "대외활동, 공모전, 인턴, 자격증, 교내활동들을 type으로, 각 활동 이름을 careerName, 카테고리들을 List<String>으로 저장합니다")
    public ResponseEntity<?> add(Long id,@RequestBody CareerRequestDto.CreateCareer careerRequestDto){
        careerService.addCareer(id,careerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
