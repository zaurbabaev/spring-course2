package az.babayev.controller;

import az.babayev.model.Computer;
import az.babayev.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService service;

    @Autowired
    public ComputerController(ComputerService service) {
        this.service = service;
    }

    @GetMapping
    public String showStudentHtml(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {
        model.addAttribute("computers", service.filter(search));
        return "/computers";

    }

    @GetMapping("/open-save-page")
    public String openSave(Model model) {
        model.addAttribute("computer", new Computer());
        model.addAttribute("header", "Computer qeydiyyatı");
        return "save-computer";
    }

    @PostMapping("/save")
    public String saveComputer(@ModelAttribute Computer computer) {
        if (computer.getId() == null) {
            service.add(computer);
        } else service.update(computer);
        return "redirect:/computers";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/computers";
    }

    @GetMapping("/edit/{id}")
    public String editIdSave(@PathVariable Integer id, Model model) {
        Computer findedComputer = service.findById(id);
        model.addAttribute("computer", findedComputer);
        model.addAttribute("header", "Computer redaktəsi");
        return "save-computer";
    }

}
