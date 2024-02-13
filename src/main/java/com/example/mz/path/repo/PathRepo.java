package com.example.mz.path.repo;


import com.example.mz.path.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PathRepo extends JpaRepository<Path, String> {
}
