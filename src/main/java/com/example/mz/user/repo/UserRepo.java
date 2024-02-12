package com.example.mz.user.repo;

import com.example.mz.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long>{
}
