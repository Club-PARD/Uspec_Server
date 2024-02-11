package com.example.mz.career.service;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.career.repo.CareerRepo;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerService {
    private final UserRepo userRepo;
    private final CareerRepo careerRepo;
    
    public void addCareer(Long userId,CareerRequestDto.CreateCareer careerRequestDto) {
        User user = userRepo.findById(userId).orElseThrow(()->new IllegalArgumentException("해당 유저가 없습니다"));
        user.setRole("ROLE_CAREER"); //이력 작성하면 ROLE_CAREER로 변경
        Career career = Career.create(careerRequestDto);
        career.setUser(user);
        careerRepo.save(career);
    }

    public List<CareerRequestDto.CareerSummary> getTop3WithPercentage(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        long totalCareers = careerRepo.count(); //careerRepo에 있는 career의 총 갯수
        Pageable topThree =  PageRequest.of(0, 3); //첫번째 페이지에서 3개의 Pageable 객체를 가져오는 요청객체
        List<Object[]> results = careerRepo.findTopTypes(user.getPath(),topThree);

        return results.stream() //results를 stream으로 변환
                .map(result -> {
                    String type = (String) result[0];
                    long count = (Long) result[1];
                    double percentage = totalCareers > 0 ? (double) count / totalCareers * 100 : 0;
                    return new CareerRequestDto.CareerSummary(type, count, percentage);
                })
                .collect(Collectors.toList());
    }

//    public List<CareerRequestDto.CareerNameSummary> getTopTypeWithTop3CareerName() {
//        // Find the most frequent type
//        Pageable topTypePage = PageRequest.of(0, 1);
//        List<Object[]> topTypeResult = careerRepo.findTopTypes(topTypePage);
//        if (topTypeResult.isEmpty()) {
//            return Collections.emptyList();
//        }
//        String mostType = (String) topTypeResult.get(0)[0];
//
//        Pageable top3Page = PageRequest.of(0, 3);
//        List<Object[]> top3Categories = careerRepo.findTopCategoriesByType(mostType, top3Page);
//
//        return top3Categories.stream()
//                .map(cn -> new CareerRequestDto.CareerNameSummary(mostType, (String) cn[0], (Long) cn[1]))
//                .collect(Collectors.toList());
//    }

    public List<CareerRequestDto.UserSpecRank> getTop3UserBySpecRank(Long userId) {
        Pageable top3User = PageRequest.of(0, 3);
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        List<Object[]> top3UserBySpecRank = careerRepo.findTop3UserBySpecRank(u.getPath(),top3User);
        return top3UserBySpecRank.stream().map(
                user -> {
                    String name = u.getName();
                    String enroll = u.getEnroll();
                    Long careerNum = (Long) user[1];
                    return new CareerRequestDto.UserSpecRank(name, enroll, careerNum);
                }
        ).collect(Collectors.toList());
    }
}
