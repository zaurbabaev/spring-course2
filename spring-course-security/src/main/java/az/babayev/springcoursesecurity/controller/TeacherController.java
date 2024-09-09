package az.babayev.springcoursesecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public String showTeachersHtml() {
        return "teachers";
    }
}
