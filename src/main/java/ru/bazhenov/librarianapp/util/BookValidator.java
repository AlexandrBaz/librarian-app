package ru.bazhenov.librarianapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bazhenov.librarianapp.models.Book;
@Component
public class BookValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (!isStringCyrillic(book.getName())){
            errors.rejectValue("fullName","", "Название книги должно быть на русском языке");
        }
        if(!isStringCyrillic(book.getAuthor())){
            errors.rejectValue("author","", "Имя автора на должно быть на русском языке");
        }
    }

    public Boolean isStringCyrillic(String str){
        return getaBoolean(str);
    }

    private static Boolean getaBoolean(String str) {
        String text = str.replaceAll("\\s|-","");
        for(int i = 0; i < text.length(); i++) {
            if(!Character.UnicodeBlock.of(text.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return false;
            }
        }
        return true;
    }
}
