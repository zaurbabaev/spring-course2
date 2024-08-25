package az.babayev.model;

public class Student {
    private Integer id;
    private String name;
    private String surname;
    private static int count;

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

    public Student() {
    }


    public Student(String name, String surname) {
        this.id = ++count;
        this.name = name;
        this.surname = surname;
    }
}
