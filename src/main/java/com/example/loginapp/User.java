package com.example.loginapp;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // STT

    @Column(name = "name", nullable = false)
    private String name;  // Tên nhân sự

    @Column(name = "employee_code", unique = true, nullable = false)
    private String employeeCode;  // Mã nhân sự

    @Column(name = "email", unique = true, nullable = false)
    private String email;  // Email

    @Column(name = "role", nullable = false)
    private String role;  // Quyền (ROLE_ADMIN, ROLE_EMPLOYEE)

    @Column(name = "status", nullable = false)
    private String status;  // Trạng thái (Active, Inactive)

    @Column(name = "password", nullable = false)
    private String password;  // Mật khẩu

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    // Setter cho password
    public void setPassword(String password) {
        this.password = password;
    }

}
