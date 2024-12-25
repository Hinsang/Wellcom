package com.Wellcom.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p WHERE p.ptitle LIKE %:text%")
    List<ProductEntity> getSearch(@Param("text") String text);

}
