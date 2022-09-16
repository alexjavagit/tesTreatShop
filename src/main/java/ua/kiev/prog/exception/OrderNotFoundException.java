package ua.kiev.prog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(Long id) {
        super("Order not found with ID " + id);
    }

}