package az.babayev.repository;

import az.babayev.enums.Sector;
import az.babayev.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final DataSource dataSource;

    @Autowired
    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> findAll(String search) {
        ArrayList<Student> students = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY = "SELECT * FROM students WHERE name LIKE '%" + search + "%' or " +
                    "surname LIKE '%" + search + "%' or address LIKE '%" + search + "%'";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .birthday(resultSet.getDate("birthday"))
                        .sector(Sector.valueOf(resultSet.getString("sector")))
                        .build();
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void add(Student student) {
        try (Connection connection = dataSource.getConnection()) {
            String INSERT_QUERY =
                    "INSERT INTO students (name, surname, email, phone, address, birthday, sector)" +
                            "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getAddress());
            statement.setDate(6, student.getBirthday());
            statement.setString(7, (student.getSector().name()));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String DELETE_QUERY = "DELETE FROM students WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Student findById(Integer id) {
        Student student = new Student();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY = "SELECT * FROM students WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setAddress(resultSet.getString("address"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setSector(Sector.valueOf(resultSet.getString("sector")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;

    }

    public void update(Student student) {
        try (Connection connection = dataSource.getConnection()) {
            String UPDATE_QUERY = "UPDATE students SET name=?, surname=?, address=?, email=?," +
                    "phone=?, birthday=?, sector=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setDate(6, student.getBirthday());
            statement.setString(7, String.valueOf(student.getSector()));
            statement.setInt(8, student.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
