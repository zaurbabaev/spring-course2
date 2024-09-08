package az.babayev.springmvcjdbctemplate.repository;

import az.babayev.springmvcjdbctemplate.model.Computer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComputerRepository {

    private final JdbcTemplate jdbcTemplate;

    public ComputerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Computer findById(Integer id) {
        String SELECT_QUERY_BY_ID = "SELECT * FROM computers WHERE id=?";
        return jdbcTemplate.queryForObject(SELECT_QUERY_BY_ID,
                new BeanPropertyRowMapper<>(Computer.class), id);
    }

    public List<Computer> findAll(String search) {
        String SELECT_QUERY = "SELECT * FROM computers WHERE model LIKE ? or brand LIKE ?";
        String searchPattern = "%" + search + "%";
        return jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(Computer.class),
                searchPattern, searchPattern);

    }

    public void add(Computer computer) {
        String INSERT_QUERY = "INSERT INTO computers (brand,model, price) VALUES (?,?,?)";
        jdbcTemplate.update(INSERT_QUERY, computer.getBrand(), computer.getModel(), computer.getPrice());
    }

    public void update(Computer computer) {
        String UPDATE_QUERY = "UPDATE computers SET brand=?, model=?, price=? WHERE id=?";
        jdbcTemplate.update(UPDATE_QUERY,
                computer.getBrand(), computer.getModel(), computer.getPrice(), computer.getId());
    }

    public void delete(Integer id) {
        String DELETE_QUERY = "DELETE FROM computers WHERE id=?";
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
