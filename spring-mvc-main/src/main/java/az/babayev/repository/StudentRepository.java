package az.babayev.repository;

import az.babayev.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private DataSource dataSource;


    public List<Student> findAll(String search) {
        ArrayList<Student> students = new ArrayList<>();
//            final String SELECT_QUERY = "SELECT * FROM students WHERE name LIKE '%" + search + "%';";
        final String SELECT_QUERY = String.format("SELECT * FROM students WHERE name LIKE '%%%s%%';", search);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    public void add(Student student) {
        final String INSERT_QUERY =
                String.format("INSERT INTO students (%s,%s) values (?,?);", "name", "surname");
        final String INSERT_QUERY2 = ("INSERT INTO students (name, surname) values (?,?);");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY2);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
