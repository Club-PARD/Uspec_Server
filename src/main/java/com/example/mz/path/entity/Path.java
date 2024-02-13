package com.example.mz.path.entity;

import com.example.mz.global.converter.ArrayConverter;
import com.example.mz.path.dto.PathResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Path {
    @Id
    private String path;

    @ElementCollection
    @CollectionTable(name = "path_categories", joinColumns = @JoinColumn(name = "path"))
    @Column(name = "category")
    private List<String> pathCategory;
}
