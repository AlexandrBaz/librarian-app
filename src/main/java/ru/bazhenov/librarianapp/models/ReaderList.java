package ru.bazhenov.librarianapp.models;

import java.util.List;

public class ReaderList {
    private Reader reader;
    private List<Book> bookList;

    public ReaderList(){}

    public ReaderList(Reader reader, List<Book> bookList) {
        this.reader = reader;
        this.bookList = bookList;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
