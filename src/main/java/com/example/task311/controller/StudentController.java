package com.example.task311.controller;

import com.example.task311.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.task311.model.Student;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceImp studentServiceImp;
    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("studentForm", new Student());
        List<Student> studentList = studentServiceImp.getAll();
        model.addAttribute("students", studentList);
        return "students";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, Model model) {
        studentServiceImp.addStudent(student);
        model.addAttribute("studentForm", new Student());
        model.addAttribute("students", studentServiceImp.getAll());
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String updateStudent(@RequestParam("id")@ModelAttribute int id, Model model) throws Exception {
    model.addAttribute("editStudentForm", new Student());
    model.addAttribute("id", id);
    model.addAttribute("student",studentServiceImp.getStudentById(id));
    return "edit";
    }
    @PostMapping("/edited")
    public String postUpdateStudent(@ModelAttribute Student newStudent, @RequestParam("id") int id, Model model) {
        studentServiceImp.updateStudentById(id, newStudent.getFirstName(), newStudent.getLastName(), newStudent.getDegree());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("id") int id, Model model) {
        studentServiceImp.deleteStudentById(id);
        model.addAttribute("studentForm", new Student());
        model.addAttribute("student", studentServiceImp.getAll());
        return "redirect:/";
    }
}
