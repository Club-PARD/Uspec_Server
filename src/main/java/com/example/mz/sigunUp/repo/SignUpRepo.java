package com.example.mz.sigunUp.repo;


import com.example.mz.sigunUp.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepo extends JpaRepository<Path, String> {
}
