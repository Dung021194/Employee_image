package com.example.employeeimage.controller;

import com.example.employeeimage.model.Employee;
import com.example.employeeimage.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    ICoreService <Employee> iCoreService;
    @GetMapping
    private ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("employees",iCoreService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    private ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }
    @PostMapping ("/save")
    private String saveEmployee(@ModelAttribute Employee employee) {
        iCoreService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/update/{id}")
    private ModelAndView update(@PathVariable Long id){
        Employee employee = iCoreService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/form");
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    private String deleteEmp(@PathVariable long id){
        iCoreService.deleteById(id);
        return "redirect:/employees";

    }


}
