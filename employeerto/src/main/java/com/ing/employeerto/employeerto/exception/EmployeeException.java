package com.ing.employeerto.employeerto.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public EmployeeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
