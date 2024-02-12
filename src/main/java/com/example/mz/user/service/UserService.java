package com.example.mz.user.service;

import com.example.mz.global.service.S3Service;
import com.example.mz.user.dto.UserRequestDto;
import com.example.mz.user.dto.UserResponseDto;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final S3Service s3Service;

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

    public UserRequestDto.Read readUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다."));
        return User.read(user);
    }

    public UserResponseDto.ImageRet updateImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        String imageUrl = s3Service.upload(image, "UserProfileImage");
        user.setImage(imageUrl);
        userRepo.save(user);
        return UserResponseDto.ImageRet.builder()
                .imageUrl(imageUrl).build();
    }
}
