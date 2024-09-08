package az.babayev.springmvcjdbctemplate.controller;

import az.babayev.springmvcjdbctemplate.model.Student;
import az.babayev.springmvcjdbctemplate.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {


    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String showStudentHtml(@RequestParam(required = false, defaultValue = "") String search,
                                  Model model) {
        model.addAttribute("student", service.findAll(search));
        return "students";
    }

    @GetMapping("/open-save-student")
    public String showSaveStudent(Model model) {
        Student student = Student.builder()
                .birthday(LocalDate.of(1940, 1, 1)).build();
        model.addAttribute("student", student);
        model.addAttribute("sectors", getSectors());
        model.addAttribute("header", "Tələbə qeydiyyatı");
        return "save-student";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Student student,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sectors", getSectors());
            String header = student.getId() == null ?
                    "Tələbə qeydiyyatı" : "Tələbə redaktəsi";
            model.addAttribute("header", header);
            return "save-student";
        }
        if (student.getId() == null) {
            service.add(student);
        } else {
            service.update(student);
        }
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Student studentFoundById = service.findById(id);
        model.addAttribute("student", studentFoundById);
        model.addAttribute("sectors", getSectors());
        model.addAttribute("header", "Tələbə redaktəsi");
        return "save-student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/students";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }


    private static Map<String, String> getSectors() {
        Map<String, String> sectors = new HashMap<>();
        sectors.put("AZ", "Azərbaycan");
        sectors.put("EN", "Ingilis");
        sectors.put("RU", "Rus");
        sectors.put("DE", "Alman");
        return sectors;
    }
}
