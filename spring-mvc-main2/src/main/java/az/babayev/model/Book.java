package az.babayev.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class Book {

    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer page;

    public Book() {
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer page) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
