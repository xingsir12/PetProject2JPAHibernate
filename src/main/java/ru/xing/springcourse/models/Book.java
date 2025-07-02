package ru.xing.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Введите название книги")
    @Size(min = 2, max = 100, message = "Название должно иметь от 2 до 100 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Введите ФИО автора книги")
    @Size(min = 2, max = 100, message = "ФИО должно иметь от 2 до 100 символов")
    private String author;

    @Column(name = "year")
    @Min(value = 1500, message = "Год должен быть больше 1500")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient //Ставим transient для того, чтобы Hibernate не замечал это поле
    private boolean expired;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
