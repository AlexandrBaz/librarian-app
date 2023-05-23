package ru.bazhenov.librarianapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bazhenov.librarianapp.models.Book;
import ru.bazhenov.librarianapp.models.BookList;
import ru.bazhenov.librarianapp.models.Reader;

import java.util.List;
import java.util.Objects;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY id ASC ", new BeanPropertyRowMapper<>(Book.class));
    }

    public void addNewBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) values (?,?,?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public BookList getBookList(int id){
        BookList bookList = new BookList();
        Book book = jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
        bookList.setBook(book);
        bookList.setReaderList(jdbcTemplate.query("SELECT * FROM reader", new BeanPropertyRowMapper<>(Reader.class)));
        bookList.setReader(jdbcTemplate.query("SELECT * FROM book JOIN reader r ON book.reader_id=r.id WHERE book.id=?", new BeanPropertyRowMapper<>(Reader.class), id).stream().findAny().orElse(null));
        bookList.setBookIsTaken(Objects.requireNonNull(book).getReader_id() != null);
        return bookList;
    }

    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void resetReader(int id) {
        jdbcTemplate.update("UPDATE book SET reader_id=null where id=?", id);
    }

    public void setBookReader(int id, int bookId) {
        jdbcTemplate.update("UPDATE book SET reader_id=? WHERE id=?", id,bookId);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book where id=?", id);
    }
}