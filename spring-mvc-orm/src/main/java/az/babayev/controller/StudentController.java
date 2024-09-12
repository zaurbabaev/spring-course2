package az.babayev.controller;

import az.babayev.entity.Student;
import az.babayev.entity.User;
import az.babayev.enums.Sector;
import az.babayev.service.StudentService;
import az.babayev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT_VIEW')")
    public String showStudentHtml(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {
        model.addAttribute("students", service.filter(search));
        return "students";

    }

    @GetMapping("/open-save-page")
    @PreAuthorize("hasAuthority('ROLE_STUDENT_ADD')")
    public String openSave(Model model) {
        Student student = Student.builder()
                .birthday((LocalDate.of(1900, 1, 1)))
                .build();
        model.addAttribute("sectors", Sector.values());
        model.addAttribute("student", student);
        model.addAttribute("header", "Tələbə qeydiyyatı");
        return "save-student";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_STUDENT_ADD')")
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
        User user = new User();
        service.add(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_STUDENT_DELETE')")
    public String deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_STUDENT_UPDATE')")
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

    @ExceptionHandler
    public String handleAccessDeniedException(AccessDeniedException exc, Model model) {
        model.addAttribute("message", exc.getMessage());
        return "my-error";
    }


}
