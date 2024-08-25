package az.babayev.controller;

import az.babayev.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    public static List<Person> personsList;

    static {
        personsList = new ArrayList<>();
        personsList.add(new Person("Zaur", 100, "Xırdalan"));
        personsList.add(new Person("Nail", 20, "Bakı"));
        personsList.add(new Person("Yusif", 15, "Şuşa"));
        personsList.add(new Person("Elnur", 40, "Sumqayıt"));
        personsList.add(new Person("Kerim", 24, "Gəncə"));
    }

    @GetMapping
    public String showPersonsHtml(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) {

        List<Person> foundPerson = personsList.stream()
                .filter(person -> person.getName()
                        .contains(search))
                .toList();
        model.addAttribute("persons", foundPerson);
        return "persons";


    }

}
