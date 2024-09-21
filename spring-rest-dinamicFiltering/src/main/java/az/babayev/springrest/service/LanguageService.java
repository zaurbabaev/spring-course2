package az.babayev.springrest.service;

import az.babayev.springrest.entity.LanguageEntity;
import az.babayev.springrest.exceptions.LanguageExistsException;
import az.babayev.springrest.exceptions.LanguageNotFoundException;
import az.babayev.springrest.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;


    public List<LanguageEntity> filter(String search) {

        return repository.findAllAndSearch(search);
    }

    public Long add(LanguageEntity request) {
        Optional<LanguageEntity> language = repository.findByLanguage(request.getLanguage());
        if (language.isPresent()) {
            findById(language.get().getId());
            throw new LanguageExistsException("Language " + request.getLanguage() + " is exists");
        }
        return repository.save(request).getId();
    }

    public LanguageEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new LanguageNotFoundException("Language with id : " + id + " not found"));
    }


}
