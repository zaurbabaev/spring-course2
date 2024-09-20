package az.babayev.springrest.service;

import az.babayev.springrest.entity.AuthorityEntity;
import az.babayev.springrest.entity.UserEntity;
import az.babayev.springrest.exceptions.MyDataAlreadyExistsException;
import az.babayev.springrest.repository.AuthorityRepository;
import az.babayev.springrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final AuthorityRepository authorityRepository;

    public void createLibrarian(UserEntity user) {

        Optional<UserEntity> byUsername = repository.findById(user.getUsername());
        if (byUsername.isPresent()) {
            throw new MyDataAlreadyExistsException("This user exists");
        }

        user.setPassword("{noop}" + user.getPassword());

        AuthorityEntity authority = new AuthorityEntity();
        authority.setUsername(user.getUsername());
        authority.setAuthority("ROLE_LIBRARIAN");
        authorityRepository.save(authority);

        repository.save(user);

    }


}
