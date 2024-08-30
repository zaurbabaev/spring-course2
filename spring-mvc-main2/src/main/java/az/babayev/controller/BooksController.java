package az.babayev.controller;


import az.babayev.model.Book;
import az.babayev.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {


    private final BookService service;

    @Autowired
    public BooksController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String showBooksHtml(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {
        model.addAttribute("books", service.filter(search));
        return "books";
    }

    @GetMapping("/open-save-page")
    public String savePage(Model model) {
        model.addAttribute("book", new Book());
        return "save-book";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        service.add(book);
        return "redirect:/books";
    }

}
