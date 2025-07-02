package ru.xing.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="full_name")
    @NotEmpty(message = "ФИО в этом поле обязательно")
    @Size(min = 2, max = 100, message = "ФИО должно иметь от 2 до 100 символов")
    private String fullName;

    @Column(name="year_of_birth")
    @Min(value = 1900, message = "Год не должен быть меньше 1900")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(int id, String full_name, int year_OF_BIRTH) {
        this.id = id;
        this.fullName = full_name;
        this.yearOfBirth = year_OF_BIRTH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
