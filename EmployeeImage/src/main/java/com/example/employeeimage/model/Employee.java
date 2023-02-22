package com.example.employeeimage.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String imagePath;
    @Transient
    private MultipartFile image;



    public Employee(Long id, String name, int age, String imagePath, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imagePath = imagePath;
        this.image = image;
    }

    public Employee(String name, int age, String imagePath, MultipartFile image) {
        this.name = name;
        this.age = age;
        this.imagePath = imagePath;
        this.image = image;
    }

    public Employee() {
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", imagePath='" + imagePath + '\'' +
                ", image=" + image +
                '}';
    }
}
