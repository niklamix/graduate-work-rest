package com.niklamix.graduateworkrest.exception.handing;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<IncorrectData>> handlerException(MethodArgumentNotValidException exception) {
        List<IncorrectData> messageList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            IncorrectData data = new IncorrectData(error.getDefaultMessage());
            messageList.add(data);
        });
        return new ResponseEntity<>(messageList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<HashMap<String, String>> handlerException(ConstraintViolationException exception) {
        HashMap<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String columnPath = violation.getPropertyPath().toString();
            int indexLastDot = columnPath.lastIndexOf(".");
            String columnName = violation.getPropertyPath().toString().substring(indexLastDot + 1, columnPath.length());
            errorMap.put(columnName, violation.getMessage());
        }
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<IncorrectData> handlerException(MethodArgumentTypeMismatchException exception) {
        String message = exception.getValue()+ " - " + "invalid parameter. You must enter an integer number";
        return new ResponseEntity<>(new IncorrectData(message), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<IncorrectData> handlerException(DataIntegrityViolationException exception) {
        String message = "This login already exists";
        return new ResponseEntity<>(new IncorrectData(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchEntityException.class, NumberFormatException.class, IllegalArgumentException.class,
            HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<IncorrectData> handlerException(RuntimeException exception) {
        IncorrectData data = new IncorrectData(exception.getMessage());
        return switch (exception.getClass().getSimpleName()) {
            case "NoSuchEntityException" -> new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            case "NumberFormatException", "IllegalArgumentException", "HttpMediaTypeNotSupportedException" ->
                    new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
