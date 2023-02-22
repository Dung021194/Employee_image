package com.example.employeeimage.service.impl;

import com.example.employeeimage.model.Employee;
import com.example.employeeimage.repository.EmployeeRepository;
import com.example.employeeimage.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements ICoreService<Employee> {
    @Autowired
    EmployeeRepository employeeRepository;

    @Value("${upload}")
    private String uploadPath;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        Employee employee =  employeeRepository.findById(id);
        return employee;
    }

    @Override
    public void save(Employee employee) {
            if (employee.getId() == null) {
                if (!employee.getImage().isEmpty()) {
                    employee.setImagePath(getFileName(employee));
                } else {
                    employee.setImagePath("TF09_Copertina_SEO.jpg");
                }
                employeeRepository.create(employee);
            } else {
                if (!employee.getImage().isEmpty()) {
                    employee.setImagePath(getFileName(employee));
                }
                    employeeRepository.update(employee);
            }
        }



    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);


    }
    public String getFileName(Employee employee) {
        MultipartFile image = employee.getImage();
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}
