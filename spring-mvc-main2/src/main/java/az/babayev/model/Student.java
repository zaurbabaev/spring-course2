package az.babayev.model;

import az.babayev.enums.Sector;
import lombok.Builder;

import java.sql.Date;
import java.time.LocalDate;

@Builder
public class Student {

    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;
    private Date birthday;
    private Sector sector;


    private int counter;

    public Student() {

    }

    public Student(Integer id, String name, String surname, String phone, String address,
                   String email, Date birthday, Sector sector, int counter) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.sector = sector;
        this.counter = counter;
    }

    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
