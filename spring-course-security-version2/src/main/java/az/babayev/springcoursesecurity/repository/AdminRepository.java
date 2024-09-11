package az.babayev.springcoursesecurity.repository;

import az.babayev.springcoursesecurity.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public void add(Admin admin) {

        String INSERT_QUERY_TO_USERS="INSERT INTO users (name, username, password, enabled) VALUES (?,?,?,?)";
        jdbcTemplate.update(INSERT_QUERY_TO_USERS,
                admin.getName(),admin.getUsername(),admin.getPassword(),1);
        String INSERT_QUERY_TO_AUTHORITIES="INSERT INTO authorities (username, authority) VALUES (?,?)";
        jdbcTemplate.update(INSERT_QUERY_TO_AUTHORITIES,admin.getUsername(),"ROLE_ADMIN");
    }


}
