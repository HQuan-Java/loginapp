package com.example.loginapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam String email, Model model) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("message", "Chúng tôi đã gửi cho bạn liên kết đặt lại mật khẩu qua email của bạn.");
        } else {
            model.addAttribute("error", "Email không tồn tại trong hệ thống.");
        }
        return "forgot-password";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "redirect:/users-list";
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu.");
            return "login";
        }
    }

    @GetMapping("/users-list")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }



}