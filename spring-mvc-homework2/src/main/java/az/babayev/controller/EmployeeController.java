package az.babayev.controller;

import az.babayev.model.Employee;
import az.babayev.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<String> positions = new ArrayList<>();

    {
        positions.add("Human resources");
        positions.add("Information technologies");
    }

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    public String showEmployeeHtml(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {
        model.addAttribute("employee", service.filter(search));
        return "employee";

    }

    @GetMapping("/open-save-employees")
    public String openSavePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "save-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        service.add(employee);
        return "redirect:/employees";
    }
}
