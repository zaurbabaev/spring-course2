package az.babayev.springcoursesecurity.controller;

import az.babayev.springcoursesecurity.model.Admin;
import az.babayev.springcoursesecurity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admins")
public class AdminController {


    @Autowired
    AdminService service;

    @GetMapping("/create-admin-account")
    public String showCreateAdminAccount(Model model) {
        model.addAttribute("admin", new Admin());
        return "create-admin";
    }

    @PostMapping("/add")
    public String receiveAdminData(@Valid @ModelAttribute
                                   Admin admin,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-admin";
        }
        // admin account (users, authorities) cədvəlinə düşəcək
        service.add(admin);
        return "redirect:/users/open-our-login";
    }

}
