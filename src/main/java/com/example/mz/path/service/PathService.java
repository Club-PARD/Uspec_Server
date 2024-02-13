package com.example.mz.path.service;

import com.example.mz.global.exception.CustomException;
import com.example.mz.global.exception.ExceptionCode;
import com.example.mz.path.dto.PathResponseDto;
import com.example.mz.path.entity.Path;
import com.example.mz.path.repo.PathRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PathService {
    private final PathRepo pathRepo;

    public PathResponseDto.PathRet readPath(String path) {
        Path p = pathRepo.findById(path).orElseThrow(() -> new CustomException(ExceptionCode.PATH_NOT_FOUNT));
        return PathResponseDto.PathRet.builder()
                .pathCategory(p.getPathCategory())
                .build();
    }

    public void createPath(PathResponseDto.PathReq req) {
        Path p = Path.builder()
                .path(req.getPath())
                .pathCategory(req.getPathCategory())
                .build();
        pathRepo.save(p);
    }
}
