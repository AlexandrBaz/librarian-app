package ru.bazhenov.librarianapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bazhenov.librarianapp.models.Book;
import ru.bazhenov.librarianapp.models.Reader;
import ru.bazhenov.librarianapp.models.ReaderList;

import java.util.List;

@Component
public class ReaderDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReaderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reader> index(){
        return jdbcTemplate.query("SELECT * FROM Reader ORDER BY id ", new BeanPropertyRowMapper<>(Reader.class));

    }

    public void addReader(Reader reader){
        jdbcTemplate.update("INSERT INTO Reader(full_name, year_of_birth) values (?,?)", reader.getFullName(), reader.getYearOfBirth());
    }

    public Reader getByIdReader(int id){
        return jdbcTemplate.query("SELECT * FROM Reader WHERE id=?", new BeanPropertyRowMapper<>(Reader.class), id)
                .stream().findAny().orElse(null);
    }

    public ReaderList getById(int id) {
        ReaderList readerList = new ReaderList();
        readerList.setReader(jdbcTemplate.query("SELECT * FROM Reader WHERE id=?", new BeanPropertyRowMapper<>(Reader.class), id)
                .stream().findAny().orElse(null));
        readerList.setBookList(jdbcTemplate.query("SELECT * FROM book WHERE reader_id=?", new BeanPropertyRowMapper<>(Book.class), id));
        return readerList;
    }

    public void readerUpdate(int id, Reader updatedReader){
        jdbcTemplate.update("UPDATE Reader SET full_name=?, year_of_birth=? WHERE id=?", updatedReader.getFullName(), updatedReader.getYearOfBirth(), id);
    }

    public void deleteReader(int id) {
        jdbcTemplate.update("DELETE FROM Reader WHERE id=?", id);
    }
}
