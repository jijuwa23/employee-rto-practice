package com.ing.employeerto.employeerto.service.impl;

import com.ing.employeerto.employeerto.dao.EmployeeRepository;
import com.ing.employeerto.employeerto.dao.ScheduleRepository;
import com.ing.employeerto.employeerto.dto.EmployeeRequest;
import com.ing.employeerto.employeerto.dto.ScheduleRequest;
import com.ing.employeerto.employeerto.entity.EmployeeEntity;
import com.ing.employeerto.employeerto.entity.ScheduleEntity;
import com.ing.employeerto.employeerto.exception.EmployeeException;
import com.ing.employeerto.employeerto.service.EmployeeRTOService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import util.DaysUtil;

import java.util.Optional;

import static com.ing.employeerto.employeerto.common.EmployeeCommon.DAY_IS_INVALID;
import static com.ing.employeerto.employeerto.common.EmployeeCommon.EMPLOYEE_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeRTOServiceImpl implements EmployeeRTOService {

    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    public EmployeeEntity addEmployee(EmployeeRequest employeeRequest) {
        Optional<EmployeeEntity> employee = employeeRepository.findByName(employeeRequest.getEmployeeName());
        if (employee.isPresent()) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST.toString(), EMPLOYEE_ALREADY_EXISTS);
        } else {
            return employeeRepository.save(EmployeeEntity.builder()
                .team(employeeRequest.getEmployeeTeam())
                .name(employeeRequest.getEmployeeName())
                .build());
        }
    }

    @Override
    public ScheduleEntity plotSchedule(ScheduleRequest scheduleRequest) {
        if (scheduleRepository.existsById(scheduleRequest.getEmployeeId())) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST.toString(), EMPLOYEE_ALREADY_EXISTS);
        } else {
            EmployeeEntity employee =
                employeeRepository.findById(scheduleRequest.getEmployeeId())
                    .orElseThrow(() -> new EmployeeException(HttpStatus.BAD_REQUEST.toString(), EMPLOYEE_ALREADY_EXISTS));

            validateDay(scheduleRequest.getFirstDay());
            validateDay(scheduleRequest.getSecondDay());

            return scheduleRepository.save(ScheduleEntity.builder()
                .employee(employee)
                .firstDay(scheduleRequest.getFirstDay())
                .secondDay(scheduleRequest.getSecondDay())
                .build());
        }
    }

    private void validateDay(String day) {
        if (!DaysUtil.isValidDay(day.toUpperCase().trim())) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST.toString(), DAY_IS_INVALID);
        }
    }

}
