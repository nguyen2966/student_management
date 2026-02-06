package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  public String getAllStudents(@RequestParam(required = false) String keyword,
      Model model) {
    List<Student> students;
    if (keyword != null && !keyword.isEmpty()) {
      // Cần viết thêm hàm searchByName trong Service/Repository
      students = service.searchByName(keyword);
    } else {
      students = service.getAll();
    }
    model.addAttribute("dsSinhVien", students);
    return "students";
  }

}
