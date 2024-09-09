package az.babayev.springcoursesecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/managers")
public class ManagerController {


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showManagersHtml() {
        return "managers";
    }
}
