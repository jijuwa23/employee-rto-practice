package com.ing.employeerto.employeerto.service;

import com.ing.employeerto.employeerto.dto.EmployeeRequest;
import com.ing.employeerto.employeerto.dto.ScheduleRequest;
import com.ing.employeerto.employeerto.entity.EmployeeEntity;
import com.ing.employeerto.employeerto.entity.ScheduleEntity;

public interface EmployeeRTOService {
    EmployeeEntity addEmployee(EmployeeRequest employeeRequest);

    ScheduleEntity plotSchedule(ScheduleRequest scheduleRequest);
}
