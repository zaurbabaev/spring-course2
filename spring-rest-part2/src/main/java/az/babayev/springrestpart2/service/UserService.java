package az.babayev.springrestpart2.service;

import az.babayev.springrestpart2.entity.AuthorityEntity;
import az.babayev.springrestpart2.entity.UserEntity;
import az.babayev.springrestpart2.repository.AuthorityRepository;
import az.babayev.springrestpart2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final AuthorityRepository authorityRepository;

    public void createLibrarian(UserEntity user) {

        user.setPassword("{noop}" + user.getPassword());

        AuthorityEntity authority = new AuthorityEntity();
        authority.setUsername(user.getUsername());
        authority.setAuthority("ROLE_LIBRARIAN");
        authorityRepository.save(authority);

        repository.save(user);

    }
}
