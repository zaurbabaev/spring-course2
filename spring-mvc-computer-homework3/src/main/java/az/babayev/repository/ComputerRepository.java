package az.babayev.repository;

import az.babayev.model.Computer;
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
public class ComputerRepository {

    private final DataSource dataSource;

    @Autowired
    public ComputerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Computer> findAll(String search) {
        ArrayList<Computer> students = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY = "SELECT * FROM computers WHERE model LIKE '%" + search + "%' or " +
                    "brand LIKE '%" + search + "%'";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Computer computer = Computer.builder()
                        .id(resultSet.getInt("id"))
                        .model(resultSet.getString("model"))
                        .brand(resultSet.getString("brand"))
                        .price(resultSet.getDouble("price"))
                        .build();
                students.add(computer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void add(Computer computer) {
        try (Connection connection = dataSource.getConnection()) {
            String INSERT_QUERY =
                    "INSERT INTO computers (model, brand, price)" +
                            "VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, computer.getModel());
            statement.setString(2, computer.getBrand());
            statement.setDouble(3, computer.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String DELETE_QUERY = "DELETE FROM computers WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Computer findById(Integer id) {
        Computer computer = new Computer();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY = "SELECT * FROM computers WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                computer.setId(resultSet.getInt("id"));
                computer.setModel(resultSet.getString("model"));
                computer.setBrand(resultSet.getString("brand"));
                computer.setPrice(resultSet.getDouble("price"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return computer;

    }

    public void update(Computer computer) {
        try (Connection connection = dataSource.getConnection()) {
            String UPDATE_QUERY = "UPDATE computers SET model=?, brand=?, price=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, computer.getModel());
            statement.setString(2, computer.getBrand());
            statement.setDouble(3, computer.getPrice());
            statement.setInt(4, computer.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
