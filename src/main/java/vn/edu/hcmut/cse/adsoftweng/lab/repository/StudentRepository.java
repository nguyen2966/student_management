package vn.edu.hcmut.cse.adsoftweng.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
    public Student getbyId(String id);
}
