package ua.kiev.prog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(Long id) {
        super("Category not found with ID " + id);
    }
    public CategoryNotFoundException(String name) { super(String.format("Category not found with Name " + name));
    }
}