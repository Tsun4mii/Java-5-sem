package bstu.shust.jprojectv2.exception;


import bstu.shust.jprojectv2.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionControllerAdvice {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Object> handleControllerException(
            ControllerException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
