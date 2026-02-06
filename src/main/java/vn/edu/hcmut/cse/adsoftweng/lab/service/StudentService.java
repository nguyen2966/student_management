package vn.edu.hcmut.cse.adsoftweng.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student getById(String id){
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword){
        return studentRepository.findByNameContainingIgnoreCase(keyword);
    }
}
