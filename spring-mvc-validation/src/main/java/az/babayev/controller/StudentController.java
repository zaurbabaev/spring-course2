package az.babayev.controller;

import az.babayev.enums.Sector;
import az.babayev.model.Student;
import az.babayev.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

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
        Student student = Student.builder()
                .birthday(Date.valueOf(LocalDate.of(1900, 1, 1)))
                .build();
        model.addAttribute("sectors", Sector.values());
        model.addAttribute("student", student);
        model.addAttribute("header", "Tələbə qeydiyyatı");
        return "save-student";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid
                              @ModelAttribute Student student,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sectors", Sector.values());
            model.addAttribute("header",
                    student.getId() == null ? "Tələbə qeydiyyatı" : "Tələbə redaktəsi");
            return "save-student";
        }
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
        model.addAttribute("header", "Tələbə redaktəsi");
        return "save-student";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);
        webDataBinder.registerCustomEditor(String.class, trimmerEditor);
    }


}
