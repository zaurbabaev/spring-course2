package az.babayev.springcoursesecurity.service;

import az.babayev.springcoursesecurity.model.Admin;
import az.babayev.springcoursesecurity.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AdminRepository repository;

    public void add(Admin admin) {
        String rawPassword = admin.getPassword();
        String encoderPassword = encoder.encode(rawPassword);
        encoderPassword = "{bcrypt}" + encoderPassword;
        admin.setPassword(encoderPassword);
        repository.add(admin);

    }


}
