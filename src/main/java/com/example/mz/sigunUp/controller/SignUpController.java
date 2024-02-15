package com.example.mz.sigunUp.controller;

import com.example.mz.sigunUp.dto.SignUpResponseDto;
import com.example.mz.sigunUp.service.SignUpService;
import com.example.mz.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UserService userService;

    @GetMapping("/{option}")
    @Operation(summary = "분야에 대한 카테고리 조회")
    public ResponseEntity<SignUpResponseDto.PathRet> readPath(@PathVariable String option) throws IOException, URISyntaxException {
        SignUpResponseDto.PathRet ret = signUpService.readPath(option);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @PostMapping("/{school}")
    @Operation(summary = "학교에 대한 정보 조회")
    public ResponseEntity<List<SignUpResponseDto.SchoolRet>> getSchool(/*@RequestBody PathResponseDto.SchoolReq req*/@PathVariable String school) throws Exception {
//        signUpService.getSchool(req);
        List<SignUpResponseDto.SchoolRet> ret = signUpService.getStringSchool(school);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
//    @PostMapping("/path")
//    public ResponseEntity<?> createPath(@RequestBody PathResponseDto.PathReq req){
//        pathService.createPath(req);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
