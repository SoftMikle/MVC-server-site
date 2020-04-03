package ua.com.alevel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseModel(error));
    }
}
