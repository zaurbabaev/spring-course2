package az.babayev.controller;

import az.babayev.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    public static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student(1, "Zaur", "Babayev"));
        students.add(new Student(2, "Vusal", "Hesenov"));
        students.add(new Student(3, "Kenan", "Taqiyev"));
        students.add(new Student(4, "Nuran", "Muradov"));
        students.add(new Student(5, "Lale", "Yusifova"));
        students.add(new Student(6, "Nurane", "Kazimova"));
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
}
