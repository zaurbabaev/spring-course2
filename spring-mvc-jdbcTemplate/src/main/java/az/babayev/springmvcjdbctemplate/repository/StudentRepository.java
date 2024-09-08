package az.babayev.springmvcjdbctemplate.repository;

import az.babayev.springmvcjdbctemplate.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll(String search) {
        String searchPattern = "%" + search + "%";
        return jdbcTemplate.query("SELECT * FROM students WHERE name LIKE ? or surname LIKE ?",
                new BeanPropertyRowMapper<>(Student.class),
                searchPattern, searchPattern);
    }

    public Student findById(Integer id) {
        String SELECT_QUERY = "SELECT * FROM students WHERE id=?";
        return jdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Student.class), id);
    }

    public void add(Student student) {
        String INSERT_QUERY = "INSERT INTO students (name, surname, birthday, phone, address, email, sector)" +
                "VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_QUERY, student.getName(), student.getSurname(), student.getBirthday(),
                student.getPhone(), student.getAddress(),
                student.getEmail(), student.getSector());
    }

    public void update(Student student) {
        String UPDATE_QUERY = "UPDATE students SET name=?,surname=?, birthday=?, phone=?,address=?," +
                "email=?, sector=? WHERE id=?";
        jdbcTemplate.update(UPDATE_QUERY, student.getName(), student.getSurname(), student.getBirthday(),
                student.getPhone(), student.getAddress(),
                student.getEmail(), student.getSector(), student.getId());
    }

    public void delete(Integer id) {
        String DELETE_QUERY = "DELETE FROM students WHERE id=?";
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
