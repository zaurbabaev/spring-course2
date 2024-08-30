package az.babayev.repository;

import az.babayev.model.Employee;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final DataSource dataSource;

    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employee> findAll(String search) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY = "SELECT * FROM employees WHERE name LIKE '%" + search + "%' or surname LIKE '%" + search + "%'" +
                    "or address LIKE '%" + search + "%' or position LIKE '%" + search + "%'";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .age(resultSet.getInt("id"))
                        .address(resultSet.getString("address"))
                        .position(resultSet.getString("position"))
                        .build();
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void add(Employee employee) {
        try (Connection connection = dataSource.getConnection()) {
            String INSERT_QUERY =
                    "INSERT INTO employees (name, surname, age,address,position) " +
                            "VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getAddress());
            statement.setString(5, employee.getPosition());
            statement.executeUpdate();

        } catch (SQLException e) {

        }
    }


}
