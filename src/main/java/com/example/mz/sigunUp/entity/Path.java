package com.example.mz.sigunUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
