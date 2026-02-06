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

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }

    public int getAge(){
        return this.age;
    }
}