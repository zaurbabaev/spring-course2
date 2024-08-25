package az.babayev.controller;

import az.babayev.model.Student;
import az.babayev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public String showStudentHtml(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) {
        model.addAttribute("students", service.filter(search));
        return "students";
    }

    @GetMapping("/open-save-page")
    public String openSave(Model model) {
        model.addAttribute("student", new Student());
        return "save-student";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        service.add(student);
        return "redirect:/students";
    }

}

