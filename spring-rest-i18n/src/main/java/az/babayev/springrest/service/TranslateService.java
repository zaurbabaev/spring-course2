package az.babayev.springrest.service;

import az.babayev.springrest.dto.TranslateDTO;
import az.babayev.springrest.entity.TranslateEntity;
import az.babayev.springrest.repository.TranslateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslateService {

    private final TranslateRepository repository;
    private final ModelMapper mapper;

    public List<TranslateDTO> findAllByLanguage(String language) {


        List<TranslateEntity> allByLanguage = repository.findAllByLanguage(language);
        return allByLanguage.stream()
                .map(languages -> mapper
                        .map(languages, TranslateDTO.class))
                .toList();
    }
}
