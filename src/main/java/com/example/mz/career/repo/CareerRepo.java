package com.example.mz.career.repo;

import com.example.mz.career.entity.Career;
import com.example.mz.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CareerRepo extends JpaRepository<Career,Long> {
    @Query(value = "SELECT c.type, COUNT(c.type) AS count FROM User u join u.career c where u.path = :path GROUP BY c.type ORDER BY count DESC")
    List<Object[]> findTopTypes(@Param("path") String path,Pageable pageable);

    //    제일 많은 type 찾는 쿼리
    @Query("SELECT c.type, COUNT(c.type) AS cnt FROM Career c GROUP BY c.type ORDER BY cnt DESC")
    Page<Object[]> findMostTypes(Pageable pageable);

    //    특정 type에 대한 상위 category 찾는 쿼리
    @Query(value = "select category, count(category) as count" +
            " from Career c join c.categories category" +
            " where c.type = :type" +
            " group by category" +
            " order by count desc")
    List<Object[]> findTopCategoriesByType(@Param("type") String type, Pageable pageable);

    // 스펙갯수 제일 많은 3명의 user 찾는 쿼리
    @Query(value = "SELECT u.id, COUNT(c) AS careerCount FROM User u JOIN u.career c where u.path = :path GROUP BY u.id order by careerCount DESC ")
    List<Object[]> findTop3UserBySpecRank(@Param("path") String path, Pageable pageable);

}
