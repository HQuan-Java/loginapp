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
        // Sử dụng phương thức mới để sắp xếp nếu bạn muốn thứ tự mặc định là mới nhất trước
        // return userRepository.findAllByOrderByIdDesc();
        // Hoặc giữ nguyên nếu không cần sắp xếp mặc định ở đây
        return userRepository.findAll();
    }

    // Lấy người dùng theo email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // *** PHƯƠNG THỨC ĐƯỢC THÊM VÀO ***
    // Lấy người dùng theo mã nhân viên
    public Optional<User> getUserByEmployeeCode(String employeeCode) {
        return userRepository.findByEmployeeCode(employeeCode); // Gọi phương thức tương ứng của Repository
    }
    // *********************************

    // Lưu người dùng (thêm mới hoặc cập nhật)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Xóa người dùng theo ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Lấy mã nhân viên cao nhất hiện tại và tạo mã mới
    public String generateNextEmployeeCode() {
        // Lấy danh sách tất cả người dùng đã sắp xếp theo ID giảm dần
        // (Hoặc cách khác hiệu quả hơn nếu bảng lớn: tìm user có employeeCode lớn nhất)
        List<User> allUsers = userRepository.findAllByOrderByIdDesc(); // Dùng phương thức đã có

        if (allUsers.isEmpty()) {
            return "NV001";
        }

        User lastUser = allUsers.get(0); // User mới nhất (ID cao nhất)

        if (lastUser.getEmployeeCode() == null || !lastUser.getEmployeeCode().matches("NV\\d+")) {
            // Nếu mã cuối không hợp lệ, thử tìm mã NV lớn nhất thực sự
            // (Phần này có thể phức tạp, tạm thời dùng cách đơn giản nếu mã luôn đúng định dạng)
            System.err.println("Mã nhân viên cuối cùng không hợp lệ hoặc null: " + lastUser.getEmployeeCode() + ". Bắt đầu lại từ NV001.");
            // Hoặc bạn có thể duyệt ngược lại danh sách để tìm mã hợp lệ cuối cùng
            return "NV001"; // Quay về mặc định nếu có vấn đề
        }

        String lastEmployeeCode = lastUser.getEmployeeCode();

        try {
            // Giả sử định dạng luôn là NVxxx
            int lastNumber = Integer.parseInt(lastEmployeeCode.substring(2));
            int nextNumber = lastNumber + 1;
            return String.format("NV%03d", nextNumber); // Đảm bảo padding 3 chữ số
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.err.println("Lỗi khi phân tích mã nhân viên cuối cùng: " + lastEmployeeCode + ". Lỗi: " + e.getMessage());
            // Nếu lỗi, trả về một giá trị mặc định hoặc dựa trên số lượng user
            long count = userRepository.count();
            return String.format("NV%03d", count + 1); // Cách dự phòng
        }
    }
}