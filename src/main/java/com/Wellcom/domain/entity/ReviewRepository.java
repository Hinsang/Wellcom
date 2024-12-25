package com.Wellcom.domain.entity;

import com.Wellcom.domain.dto.ReviewResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query(value = "SELECT r.rid, r.stars, r.comment, r.date, m.name FROM review r JOIN member m ON r.mid = m.mid "
                + "JOIN product p ON r.pid = p.pid WHERE r.pid = :pid", nativeQuery = true)
    List<Object[]> findByPid(int pid);

}
