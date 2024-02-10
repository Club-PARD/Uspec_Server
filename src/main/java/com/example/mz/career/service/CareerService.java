package com.example.mz.career.service;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.career.repo.CareerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {
    private final CareerRepo careerRepo;


    public void addCareer(CareerRequestDto.Create careerRequestDto) {
        Career career = Career.create(careerRequestDto);
        careerRepo.save(career);
    }
}
