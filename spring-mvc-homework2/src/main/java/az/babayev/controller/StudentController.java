package az.babayev.controller;

import az.babayev.model.Student;
import az.babayev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    Map<String,String> sectorList = new HashMap<>();
    {
        sectorList.put("AZ","Azərbaycan");
        sectorList.put("RU","Rus");
        sectorList.put("EN","Ingilis");
    }

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String showStudentHtml(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {
        model.addAttribute("students", service.filter(search));
        return "/students";

    }

    @GetMapping("/open-save-page")
    public String openSave(Model model) {

        model.addAttribute("sectors", sectorList);
        model.addAttribute("student", new Student());
        return "save-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.add(student);
        return "redirect:/students";
    }
}
