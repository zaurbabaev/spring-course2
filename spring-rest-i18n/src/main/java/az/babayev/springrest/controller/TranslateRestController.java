package az.babayev.springrest.controller;

import az.babayev.springrest.dto.TranslateDTO;
import az.babayev.springrest.entity.TranslateEntity;
import az.babayev.springrest.service.TranslateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/translate")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TranslateRestController {

    private final TranslateService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Translate", description = "Method: translate")
    public List<TranslateDTO> translate(@RequestHeader("Accept-Language")Locale locale){
        String language = locale.getLanguage();
        return service.findAllByLanguage(language);
    }
}
