package az.babayev.springcore;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private int id;
    private String name;
    private double price;
    private int pageCount;

    public Book() {
        this.id = 1;
        this.name = "Java Head First";
        this.price = 70.8;
        this.pageCount = 1000;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}

