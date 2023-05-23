package ru.bazhenov.librarianapp.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

public class Reader {
    private int id;

    @NotEmpty(message = "Введите Фамилию Имя Отчество")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Формат ввода, с большой буквы, разделенный пробелами")
    private String fullName;

    @Range(min=1900, max=2023, message = "Год рождения между 1900 и 2023")
    private int yearOfBirth;

    public Reader(){}

    public Reader(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
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
}
