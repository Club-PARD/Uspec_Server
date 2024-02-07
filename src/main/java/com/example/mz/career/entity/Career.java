package com.example.mz.career.entity;

import com.example.mz.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long careerId;
    private String careerName;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "careerId")
    private List<Category> categories;
}
