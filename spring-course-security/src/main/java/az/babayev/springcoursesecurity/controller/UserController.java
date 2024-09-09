package az.babayev.springcoursesecurity.controller;

import az.babayev.springcoursesecurity.model.Admin;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/open-our-login")
    public String showOurLogin() {
        return "our-login";
    }


//    SecurityContextLogoutHandler: Session və Security kontekstini təmizləyir.
//            authentication != null: İstifadəçinin daxil olub-olmadığını yoxlayır.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/users/open-our-login";
    }


}