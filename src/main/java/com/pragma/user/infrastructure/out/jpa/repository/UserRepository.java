package com.pragma.user.infrastructure.out.jpa.repository;

import com.pragma.user.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
