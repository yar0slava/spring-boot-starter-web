package kma.topic3.webstarter.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handle(final MethodArgumentNotValidException ex) {
        final BindingResult bindingResult = ex.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        final List<String> errors = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(errors);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List<String>> handle(final ConstraintViolationException ex) {
//        final Set<ConstraintViolation<?>> fieldErrors = ex.getConstraintViolations();
//
//        final List<String> errors = fieldErrors.stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//
//        for (String s: errors) {
//            System.out.println(s);
//        }
//
//        return ResponseEntity.badRequest()
//                .body(errors);
//    }
}
