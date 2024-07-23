package com.example.CRUD_Board_withSpringSecurity.Member.repository;

import com.example.CRUD_Board_withSpringSecurity.Member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository  extends JpaRepository<Member, Long> {
    Optional<Member> findByuserId(String userId);
}
