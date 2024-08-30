package az.babayev.repository;

import az.babayev.model.Book;
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
public class BookRepository {

    private final DataSource dataSource;

    @Autowired
    public BookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Book> findAll(String search) {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String SELECT_QUERY =
                    "SELECT * FROM books WHERE name LIKE '%" + search + "%' or author LIKE '%" + search + "%'";
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .author(resultSet.getString("author"))
                        .price(resultSet.getBigDecimal("price"))
                        .page(resultSet.getInt("page"))
                        .build();
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public void add(Book book) {
        try (Connection connection = dataSource.getConnection()) {
            String INSERT_QUERY = "INSERT INTO books (name, author, price, page) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1,book.getName());
            statement.setString(2,book.getAuthor());
            statement.setBigDecimal(3,book.getPrice());
            statement.setInt(4,book.getPage());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
