package com.example.mz.user.service;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.career.repo.CareerRepo;
import com.example.mz.global.exception.CustomException;
import com.example.mz.global.exception.ExceptionCode;
import com.example.mz.global.exception.MZExceptionHandler;
import com.example.mz.global.pagination.ScrollPaginCollection;
import com.example.mz.global.service.S3Service;
import com.example.mz.user.dto.UserRequestDto;
import com.example.mz.user.dto.UserResponseDto;
import com.example.mz.user.entity.User;
import com.example.mz.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        User user = userRepo.findById(id).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        userRequestDto.updateEntity(user);
        userRepo.save(user);
    }

    public UserRequestDto.Read readUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        return User.read(user);
    }

    public UserResponseDto.ImageRet updateImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        String imageUrl = s3Service.upload(image, "UserProfileImage");
//        String imageUrl = s3Service.upload(image, "image"+userId.toString());
        user.setImage(imageUrl);
        userRepo.save(user);
        return UserResponseDto.ImageRet.builder()
                .imageUrl(imageUrl).build();
    }

    public List<UserResponseDto.UserCareerInfo> getUserBySpecRank(Long userId,int pageId){
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
        String path = user.getPath();

        //path같은 사람들 Career 갯수 기준으로 전체 랭킹 매긴다
        PageRequest pageRequest = PageRequest.of(pageId, 10);
        List<Object[]> careers = userRepo.makeRankByCareercountInPath(path,pageRequest);

        return careers.stream()
                .map(result -> {
                    Long id = (Long) result[0];
                    Long careerCount = (Long) result[1];
                    User user1 = userRepo.findById(id).orElseThrow(() -> new CustomException(ExceptionCode.USERID_NOT_FOUND));
                    return new UserResponseDto.UserCareerInfo(user1.getName(), user1.getEnroll(), careerCount);
                })
                .collect(Collectors.toList());
    }

}
