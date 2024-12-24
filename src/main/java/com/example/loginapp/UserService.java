package com.example.loginapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Lấy người dùng theo email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Thêm người dùng mới
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Cập nhật người dùng
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setEmployeeCode(updatedUser.getEmployeeCode());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setStatus(updatedUser.getStatus());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
        }
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
