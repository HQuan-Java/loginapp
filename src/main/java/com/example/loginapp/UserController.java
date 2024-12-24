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
    private UserService userService;  // Inject UserService vào controller

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Trả về view login.html
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // Trả về view forgot-password.html
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam String email, Model model) {
        Optional<User> user = userService.getUserByEmail(email);  // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu không
        if (user.isPresent()) {
            // Giả sử bạn gửi email khôi phục mật khẩu thành công ở đây.
            model.addAttribute("message", "Chúng tôi đã gửi cho bạn liên kết đặt lại mật khẩu qua email của bạn.");
        } else {
            model.addAttribute("error", "Email không tồn tại trong hệ thống.");
        }
        return "forgot-password"; // Trả về trang forgot-password.html với thông báo
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<User> user = userService.getUserByEmail(email);  // Lấy thông tin người dùng theo email
        if (user.isPresent() && user.get().getPassword().equals(password)) {  // Kiểm tra mật khẩu
            // Nếu đăng nhập thành công, chuyển hướng đến trang user list
            return "redirect:/users-list";  // Điều hướng đến trang user list
        } else {
            // Nếu thất bại, quay lại trang login với thông báo lỗi
            model.addAttribute("error", "Sai email hoặc mật khẩu.");
            return "login";
        }
    }

    @GetMapping("/users-list")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list"; // Trả về view users-list.html
    }



}