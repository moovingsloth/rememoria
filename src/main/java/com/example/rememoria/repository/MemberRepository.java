package com.example.rememoria.repository;

import com.example.rememoria.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname); // Ensure this method is correctly defined

    boolean existsByNickname(String nickname);
}
