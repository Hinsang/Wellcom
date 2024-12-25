package com.Wellcom.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    // 개발하면서 공부한 쿼리 내용 정리
    /*

        SQL - SQL은 데이터베이스 테이블과 직접 상호작용하며 표준 SQL 문법을 따릅니다.
        ex) SELECT p.* FROM product p ORDER BY p.pid DESC;

        HQL - HQL은 Hibernate 엔티티 객체와 상호작용하며 객체 지향적으로 데이터베이스에 접근할 수 있도록 합니다. (즉, 하이버네이트 쿼리 언어라는 의미)
        ex) @Query("SELECT p FROM ProductEntity p ORDER BY p.pid DESC")
            List<ProductEntity> findAllDesc();

        Repository @Query에서 "value ="을 사용하는 것은 선택사항이며 "*"를 사용하지 않고 alias(엔티티 별칭)를 사용해서 표현해야 합니다.

     */

    @Query("SELECT p FROM ProductEntity p ORDER BY p.pid DESC")
    List<ProductEntity> findAllDesc();

    @Query("SELECT p FROM ProductEntity p WHERE p.pid = :pid")
    Optional<ProductEntity> findByPid(@Param("pid") int pid);

    List<ProductEntity> findByPidIn(List<Integer> pids);

}
