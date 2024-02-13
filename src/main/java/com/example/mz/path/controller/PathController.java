package com.example.mz.path.controller;

import com.example.mz.path.dto.PathResponseDto;
import com.example.mz.path.service.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PathController {
    private final PathService pathService;

    @GetMapping("/option")
    public ResponseEntity<PathResponseDto.PathRet> readPath(@RequestParam("path") String path){
        PathResponseDto.PathRet ret = pathService.readPath(path);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @PostMapping("/path")
    public ResponseEntity<?> createPath(@RequestBody PathResponseDto.PathReq req){
        pathService.createPath(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
