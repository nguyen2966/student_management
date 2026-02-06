package vn.edu.hcmut.cse.adsoftweng.lab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
@Controller
@RequestMapping("/students")
public class StudentWebController {
@Autowired
private StudentService service;
// Route: GET http://localhost:8080/students
@GetMapping
public String getAllStudents(Model model) {
    // 1. Lấy dữ liệu từ Service
    List<Student> students = service.getAll();
    // 2. Đóng gói dữ liệu vào "Model" để chuyển sang View
    // Key "dsSinhVien" sẽ được dùng bên file HTML
    model.addAttribute("dsSinhVien", students);
    // 3. Trả về tên của View (không cần đuôi .html)

    return "students";
  }
}
