package az.babayev.model;

import lombok.Builder;

import java.util.Map;

@Builder
public class Employee {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String address;
    private String position;

    public Employee(Integer id, String name, String surname, Integer age, String address, String  position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.position = position;
    }

    public Employee() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String  getPosition() {
        return position;
    }

    public void setPosition(String  position) {
        this.position = position;
    }
}
