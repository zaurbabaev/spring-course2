package az.babayev.springcoursesecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_TEACHERS')")
    public String showTeachersHtml() {
        return "teachers";
    }

    // ROLE_SAVE_TEACHER bu role qeydiyyat səhifəsini açan və həmin səhifəni qeydiyyat edənə verilir.
    // ROLE_DELETE_TEACHER
    // ROLE_UPDATE_TEACHER
}
