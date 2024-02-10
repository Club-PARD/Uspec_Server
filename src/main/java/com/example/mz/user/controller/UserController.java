package com.example.mz.user.controller;

import com.example.mz.user.dto.UserRequestDto;
import com.example.mz.user.dto.UserResponseDto;
import com.example.mz.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
