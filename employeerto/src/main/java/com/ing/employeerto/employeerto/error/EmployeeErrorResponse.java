package com.ing.employeerto.employeerto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeErrorResponse {
    private String errorMessage;
    private String errorCode;
}
