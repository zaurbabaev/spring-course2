package az.babayev.service;

import az.babayev.entity.Authority;
import az.babayev.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {


    private final AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }




}
