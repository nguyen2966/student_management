package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

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

  @GetMapping("/{id}")
  public String getStudentById(@PathVariable("id") String id, Model model ) {
    Student student = service.getById(id);
    model.addAttribute("sinhvien",student);
    return "studentInfo";
  }

   @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

  @PostMapping("/{id}/update")
  public String updateStudent(@PathVariable("id") String id, @ModelAttribute("student") Student formStudent) {
      // 1. Lấy dữ liệu cũ đang "xịn" từ DB ra
      Student existingStudent = service.getById(id);
      
      if (existingStudent != null) {
          // 2. Chỉ cập nhật những trường người dùng được phép sửa
          existingStudent.setName(formStudent.getName());
          existingStudent.setEmail(formStudent.getEmail());
          existingStudent.setAge(formStudent.getAge());
          
          // 3. Lưu đối tượng cũ đã được cập nhật (các trường khác sẽ không bị mất)
          service.save(existingStudent);
      }
      
      return "redirect:/students/" + id;
  }

  @PostMapping("/{id}/delete")
  public String deleteStudent(@PathVariable("id") String id){
    Student student = service.getById(id);
    service.delete(student);
    return "redirect:/students";
  }

  @PostMapping("/create")
  public String createStudent(@ModelAttribute("student") Student student){
    service.save(student);
    return "redirect:/students";
  }

  @GetMapping("/create-form")
  public String getCreateForm(){
    return "createStudent";
  }
}

