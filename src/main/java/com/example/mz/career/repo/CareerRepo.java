package com.example.mz.career.repo;

import com.example.mz.career.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepo extends JpaRepository<Career,Long> {
}
