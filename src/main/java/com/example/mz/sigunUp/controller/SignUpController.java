package com.example.mz.sigunUp.controller;

import com.example.mz.sigunUp.dto.SignUpResponseDto;
import com.example.mz.sigunUp.service.SignUpService;
import com.example.mz.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UserService userService;

    @GetMapping("/option")
    public ResponseEntity<SignUpResponseDto.PathRet> readPath(@RequestParam("path") String path){
        SignUpResponseDto.PathRet ret = signUpService.readPath(path);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @PostMapping("/{school}")
    public ResponseEntity<SignUpResponseDto.SchoolRet> getSchool(/*@RequestBody PathResponseDto.SchoolReq req*/@PathVariable String school) throws Exception {
//        signUpService.getSchool(req);
        SignUpResponseDto.SchoolRet ret = signUpService.getStringSchool(school);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
//    @PostMapping("/path")
//    public ResponseEntity<?> createPath(@RequestBody PathResponseDto.PathReq req){
//        pathService.createPath(req);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
