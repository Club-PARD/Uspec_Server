package com.example.mz.user.service;

import com.example.mz.user.dto.UserRequestDto;
import com.example.mz.user.dto.UserResponseDto;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserResponseDto.CreateRet addUser(UserRequestDto.Create userRequestDto) {
        User user = User.create(userRequestDto);
        userRepo.save(user);
        return UserResponseDto.CreateRet.builder()
                .id(user.getId()).build();
    }

    public void updateUser(Long id, UserRequestDto.Update userRequestDto) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        userRequestDto.updateEntity(user);
        userRepo.save(user);
    }
}
