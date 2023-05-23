package ru.bazhenov.librarianapp.models;

import java.util.List;

public class BookList {
    private Book book;
    private Boolean bookIsTaken;
    private Reader reader;
    private List<Reader>readerList;
    public BookList(){}

    public BookList(Book book, Boolean bookIsTaken, Reader reader, List<Reader> readerList) {
        this.book = book;
        this.bookIsTaken = bookIsTaken;
        this.reader = reader;
        this.readerList = readerList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean getBookIsTaken() {
        return bookIsTaken;
    }

    public void setBookIsTaken(Boolean bookIsTaken) {
        this.bookIsTaken = bookIsTaken;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public void setReaderList(List<Reader> readerList) {
        this.readerList = readerList;
    }
}
