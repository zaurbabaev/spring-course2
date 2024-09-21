package az.babayev.springrest.service;

import az.babayev.springrest.dto.StudentWithNotesDTO;
import az.babayev.springrest.entity.StudentWithNotesEntity;
import az.babayev.springrest.repository.StudentWithNotesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentWithNotesService {

    private final StudentWithNotesRepository repository;
    private final ModelMapper mapper;

    public List<StudentWithNotesDTO> findAll() {

        List<StudentWithNotesEntity> studentWithNotesEntityList = repository.findAll();

        return studentWithNotesEntityList.stream()
                .map(studentWithNotesEntity -> mapper
                        .map(studentWithNotesEntity, StudentWithNotesDTO.class))
                .toList();
    }
}
