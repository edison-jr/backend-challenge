package oliveira.edison.backendchallenge.controller.password.handler;

import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PasswordExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { PasswordPatternException.class })
    protected ResponseEntity<Object> handleConflict(PasswordPatternException ex, WebRequest request) {
        String response = String.format("Invalid password: %s", ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
