package az.babayev.service;

import az.babayev.entity.Authority;
import az.babayev.entity.User;
import az.babayev.repository.AuthorityRepository;
import az.babayev.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository repository;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository repository, AuthorityRepository authorityRepository) {
        this.repository = repository;
        this.authorityRepository = authorityRepository;

    }


    public void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String rawPassword = passwordEncoder.encode(password);
        user.setPassword("{bcrypt}" + rawPassword);
        user.setEnabled(true);

        Authority authority = new Authority();
        authority.setAuthority("ROLE_STUDENT_VIEW");
        authority.setUsername(username);
        authorityRepository.save(authority);
        repository.save(user);
    }
}
