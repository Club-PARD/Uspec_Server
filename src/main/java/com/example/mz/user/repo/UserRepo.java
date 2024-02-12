package com.example.mz.user.repo;

import com.example.mz.career.dto.CareerRequestDto;
import com.example.mz.career.entity.Career;
import com.example.mz.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.mz.user.dto.UserResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    @Query(value = "SELECT u.id, COUNT(c) AS careerCount FROM User u JOIN u.career c where u.path = :path GROUP BY u.id order by careerCount DESC ")
    List<Object[]> makeRankByCareercountInPath(@Param("path") String path, Pageable pageable);
}
