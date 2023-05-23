package ru.bazhenov.librarianapp.models;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Book {
    private int id;
    private Integer reader_id;
    @NotEmpty(message = "Введите название книги")
    private String name;
    @NotEmpty(message = "Введите автора книги")
    private String author;
    @Range(min=1000, max=2023, message = "Введите год выпуска")
    private int year;

    public Book(){}

    public Book(int id, int readerId, String name, String author, int year, Integer reader_id) {
        this.id = id;
        this.reader_id = readerId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getReader_id() {
        return reader_id;
    }

    public void setReader_id(Integer reader_id) {
        this.reader_id = reader_id;
    }
}
