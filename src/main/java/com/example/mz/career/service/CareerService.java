package com.example.mz.career.service;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.career.repo.CareerRepo;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {
    private final UserRepo userRepo;
    private final CareerRepo careerRepo;
    
    public void addCareer(Long userId,CareerRequestDto.CreateCareer careerRequestDto) {
        User user = userRepo.findById(userId).orElseThrow(()->new IllegalArgumentException("해당 유저가 없습니다"));
        Career career = Career.create(careerRequestDto);
        career.setUser(user);
        careerRepo.save(career);
    }
}
