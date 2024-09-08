package az.babayev.springmvcjdbctemplate.controller;

import az.babayev.springmvcjdbctemplate.model.Computer;
import az.babayev.springmvcjdbctemplate.service.ComputerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService service;

    public ComputerController(ComputerService service) {
        this.service = service;
    }

    @GetMapping
    public String showComputerHtml(@RequestParam(required = false, defaultValue = "")
                                   String search,
                                   Model model) {
        model.addAttribute("computers", service.findAll(search));
        return "computer/computers";
    }

    @GetMapping("/open-save-computer")
    public String openSaveComputer(Model model) {
        model.addAttribute("computers", new Computer());
        model.addAttribute("header", "Computer qeydiyyatı");
        return "computer/save-computer";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Computer computer) {
        if (computer.getId() == null) {
            service.add(computer);
        } else {
            service.update(computer);
        }
        return "redirect:/computers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Computer computer = service.findById(id);
        model.addAttribute("computers", computer);
        model.addAttribute("header", "Computer redaktəsi");
        return "computer/save-computer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/computers";
    }

}
