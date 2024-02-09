package com.example.mz.user.repo;

import com.example.mz.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
}
