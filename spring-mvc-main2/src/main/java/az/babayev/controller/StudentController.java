package az.babayev.controller;

import az.babayev.enums.Sector;
import az.babayev.model.Student;
import az.babayev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

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
        model.addAttribute("sectors", Sector.values());
        model.addAttribute("student", new Student());
        model.addAttribute("header","Tələbə qeydiyyatı");
        return "save-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        if (student.getId() == null) {
            service.add(student);
        } else service.update(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editIdSave(@PathVariable Integer id, Model model) {
        Student findedStudent = service.findById(id);
        model.addAttribute("sectors", Sector.values());
        model.addAttribute("student", findedStudent);
        model.addAttribute("header","Tələbə redaktəsi");
        return "save-student";
    }

}
