package com.Wellcom.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    MemberEntity findByUsername(String username);

    boolean existsByUsername(String admin);

    MemberEntity findByMid(int mid);

}
