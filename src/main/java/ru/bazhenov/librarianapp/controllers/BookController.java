package ru.bazhenov.librarianapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bazhenov.librarianapp.dao.BookDao;
import ru.bazhenov.librarianapp.models.Book;
import ru.bazhenov.librarianapp.models.Reader;
import ru.bazhenov.librarianapp.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDao bookDao, BookValidator bookValidator) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("book", bookDao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDao.addNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model, @ModelAttribute("reader")Reader reader){
        model.addAttribute("bookList", bookDao.getBookList(id));
        return "books/show";
    }

    @PatchMapping("/reset-{id}")
    public String resetReader(@PathVariable("id") int id){
        bookDao.resetReader(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDao.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/set-reader{id}")
    public String setReader(@ModelAttribute("reader") Reader reader, @ModelAttribute("id") int bookId){
        bookDao.setBookReader(reader.getId(),bookId);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}