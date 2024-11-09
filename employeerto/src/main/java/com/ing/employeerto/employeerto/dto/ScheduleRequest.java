package com.ing.employeerto.employeerto.dto;

import lombok.Data;

@Data
public class ScheduleRequest {
    private Long employeeId;
    private String firstDay;
    private String secondDay;
}
