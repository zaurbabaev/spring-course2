package az.babayev.controller;

import az.babayev.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    public static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student("Zaur", "Babayev"));
        students.add(new Student("Vusal", "Hesenov"));
        students.add(new Student("Kenan", "Taqiyev"));
        students.add(new Student("Nuran", "Muradov"));
        students.add(new Student("Lale", "Yusifova"));
        students.add(new Student("Nurane", "Kazimova"));
    }

    @GetMapping
    public String showStudentHtml(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) {
        List<Student> filteredList = students.stream()
                .filter(student -> student.getName()
                        .contains(search))
                .toList();
        model.addAttribute("students", filteredList);
        return "students";
    }

    @GetMapping("/open-save-page")
    public String openSave(Model model) {
        model.addAttribute("student", new Student());
        return "save-student";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        students.add(new Student(student.getName(), student.getSurname()));
        return "redirect:/students";
    }

}

