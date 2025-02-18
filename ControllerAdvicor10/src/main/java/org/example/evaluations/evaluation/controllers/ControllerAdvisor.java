package org.example.evaluations.evaluation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerAdvisor {
    //Add Exception handlers here

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Create the error message and return it with status 400
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        // Create the error message and return it with status 400
        String errorMessage =  ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException  ex) {
        String errorMessage = "Something went bad at our side";
        return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(500));
    }
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            NumberFormatException.class,
    })
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(Exception   ex) {
        String errorMessage = "Please pass userId or cartId in correct format";
        return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(409));
    }
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> handleNumberFormatException (NumberFormatException    ex) {
//        String errorMessage = "Please pass userId or cartId in correct format";
//        return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(409));
//    }
}
