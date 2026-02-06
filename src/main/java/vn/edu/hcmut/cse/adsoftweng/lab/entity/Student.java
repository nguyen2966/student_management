package vn.edu.hcmut.cse.adsoftweng.lab.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id; 
    private String name;
    private String email;
    private int age;
}