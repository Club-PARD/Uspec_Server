package com.example.mz.user.controller;

import com.example.mz.user.dto.UserRequestDto;
import com.example.mz.user.dto.UserResponseDto;
import com.example.mz.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

   @PostMapping("")
    public ResponseEntity<UserResponseDto.CreateRet> add(@RequestBody UserRequestDto.Create userRequestDto){
        UserResponseDto.CreateRet ret = userService.addUser(userRequestDto);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserRequestDto.Create> update(@PathVariable Long id, @RequestBody UserRequestDto.Update userRequestDto){
        userService.updateUser(id, userRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestDto.Read> read(@PathVariable Long id){
        UserRequestDto.Read ret = userService.readUser(id);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/uploadImage", consumes = "multipart/form-data")
    public ResponseEntity<UserResponseDto.ImageRet> uploadImage(@PathVariable Long id,@RequestPart MultipartFile image) throws IOException {
       UserResponseDto.ImageRet ret = userService.updateImage(id, image);
       return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @GetMapping("/{id}/scroll/{pageId}")
    public ResponseEntity<?> getAllRankByScroll(@PathVariable Long id,@PathVariable int pageId){
        List<UserResponseDto.UserCareerInfo> ret = userService.getUserBySpecRank(id,pageId);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

}
