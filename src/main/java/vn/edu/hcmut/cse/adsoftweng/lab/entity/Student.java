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

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAge(int age){
        this.age = age;
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