package ru.bazhenov.librarianapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bazhenov.librarianapp.models.Reader;


@Component
public class ReaderValidator implements Validator {
    private final BookValidator bookValidator;
    @Autowired
    public ReaderValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if (!isStringCyrillic(reader.getFullName())){
            errors.rejectValue("fullName","", "ФИО должно быть на русском языке");
        }
    }
    private Boolean isStringCyrillic(String fullName){
        return bookValidator.isStringCyrillic(fullName);
    }
}
