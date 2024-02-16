package com.example.mz.career.service;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.career.repo.CareerRepo;
import com.example.mz.global.exception.CustomException;
import com.example.mz.global.exception.ExceptionCode;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareerService {
    private final UserRepo userRepo;
    private final CareerRepo careerRepo;
    
    public void addCareer(Long userId,CareerRequestDto.CreateCareer careerRequestDto) {
        User user = userRepo.findById(userId).orElseThrow(()->new CustomException(ExceptionCode.USERID_NOT_FOUND));
        user.setRole("ROLE_CAREER"); //이력 작성하면 ROLE_CAREER로 변경
        Career career = Career.create(careerRequestDto);
        career.setUser(user);
        if(career.getStart()!=null && career.getEnd()!=null){
            long monthsBetween = ChronoUnit.MONTHS.between(career.getStart(), career.getEnd());
            career.setMonth((int) monthsBetween);
        } else {
            career.setMonth(0);
        }
        careerRepo.save(career);
    }

    public List<CareerRequestDto.CareerSummary> getTop3WithPercentage(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        long totalCareers = careerRepo.count(); //careerRepo에 있는 career의 총 갯수
        Pageable topThree =  PageRequest.of(0, 5); //첫번째 페이지에서 3개의 Pageable 객체를 가져오는 요청객체
        List<Object[]> results = careerRepo.findTopTypes(user.getPath(),topThree);

        return results.stream() //results를 stream으로 변환
                .map(result -> {
                    String type = (String) result[0];
                    long count = (Long) result[1];
                    long percentage = totalCareers > 0 ? Math.round((double) count / totalCareers * 100) : 0;
                    return new CareerRequestDto.CareerSummary(type, count, percentage);
                })
                .collect(Collectors.toList());
    }

    public List<CareerRequestDto.CareerNameSummary> getTopTypeWithTop3CareerName(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        Pageable topTypePage = PageRequest.of(0, 1);
        List<Object[]> topTypeResult = careerRepo.findTopTypes(user.getPath(),topTypePage);
        if (topTypeResult.isEmpty()) {
            return Collections.emptyList();
        }
        Pageable top3Page = PageRequest.of(0, 3);
        String mostType = (String) topTypeResult.get(0)[0];
        if(mostType.equals("인턴")){
            return getTop3CategoryForIntern(userId);
        }
        else if(mostType.equals("자격증")){
            return getTop3Certification(userId);
        }
        List<Object[]> top3Categories = careerRepo.findTopCategoriesByType(mostType, top3Page);
        return getTop3CategoryPerType(userId,mostType);
    }

    public List<CareerRequestDto.CareerNameSummary> getTop3CategoryPerType(Long userId,String type) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        Pageable top3Page = PageRequest.of(0, 3);
        List<Object[]> top3Categories = careerRepo.findTopCategoriesByType(type, top3Page);
        return top3Categories.stream()
                .map(objects -> {
                    String category = (String) objects[0];
                    Long count = (Long) objects[1];
                    Long total = careerRepo.findCountByType(type);
                    Long mean = total /userRepo.count();
                    return CareerRequestDto.CareerNameSummary.forGeneralInfo(type,category, count, mean);
                }).collect(Collectors.toList());
    }
//인턴 상위 3개 가져오는 api
    public List<CareerRequestDto.CareerNameSummary> getTop3CategoryForIntern(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        String type = "인턴";
        Pageable top3Page = PageRequest.of(0, 3);
        List<Object[]> top3Categories = careerRepo.findTopCareerNamesByType(type, top3Page);
        return top3Categories.stream()
                .map(objects -> {
                    String careerName = (String) objects[0];
                    Integer monthForSelection = (Integer) objects[1];
                    String monthForDisplay;
                    if(monthForSelection <3){
                        monthForDisplay= "3개월 이하";
                    } else if(monthForSelection <6 && monthForSelection >=3){
                        monthForDisplay= "3~6개월";
                    } else if(monthForSelection <12 && monthForSelection >=6){
                        monthForDisplay= "6개월~1년";
                    } else {
                        monthForDisplay= "1년 이상";
                    }
                    String role = (String) objects[2];
                    Long total = careerRepo.findCountByType(type);
                    Long mean = total / userRepo.count();
                    return CareerRequestDto.CareerNameSummary.forInternInfo(type, careerName, monthForDisplay, mean, role);
                }).collect(Collectors.toList());
    }

//    자격증 제일 많이 딴 순서대로 3개 보여주는 api
    public List<CareerRequestDto.CareerNameSummary> getTop3Certification(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        String type = "자격증";
        Pageable top3Page = PageRequest.of(0, 3);
        List<Object[]> top3Categories = careerRepo.findTopCertificationByType(type,top3Page);
        return top3Categories.stream()
                .map(objects -> {
                    String careerName = (String) objects[0];
                    Long count = (Long) objects[1];
                    Long mean = count / userRepo.count();
                    return CareerRequestDto.CareerNameSummary.forCertificationInfo(careerName, count, mean);
                }).collect(Collectors.toList());
    }

    public List<CareerRequestDto.UserSpecRank> getTop3UserBySpecRank(Long userId) {
        Pageable top3User = PageRequest.of(0, 3);
        User u = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        List<Object[]> top3UserBySpecRank = careerRepo.findTop3UserBySpecRank(u.getPath(),top3User);
        return top3UserBySpecRank.stream().map(
                objects -> {
                    Long userIdFromQuery = (Long) objects[0]; // 쿼리로부터 얻은 userId
                    User user = userRepo.findById(userIdFromQuery).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
                    Long careerNum = (Long) objects[1]; // Career의 개수
                    return new CareerRequestDto.UserSpecRank(user.getName(), user.getEnroll(), careerNum);
                }
        ).collect(Collectors.toList());
    }
}
