package com.example.loginapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm kiếm người dùng bằng email
    Optional<User> findByEmail(String email);
}
