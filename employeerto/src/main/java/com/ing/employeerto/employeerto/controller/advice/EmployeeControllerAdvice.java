package com.ing.employeerto.employeerto.controller.advice;

import com.ing.employeerto.employeerto.controller.EmployeeController;
import com.ing.employeerto.employeerto.error.EmployeeErrorResponse;
import com.ing.employeerto.employeerto.exception.EmployeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {EmployeeController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeException.class)
    public final ResponseEntity<EmployeeErrorResponse> handleEmployeeException(EmployeeException ex) {
        return new ResponseEntity<>(EmployeeErrorResponse.builder()
            .errorCode(ex.getErrorCode())
            .errorMessage(ex.getMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

}
