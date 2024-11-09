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

import static com.ing.employeerto.employeerto.common.EmployeeCommon.*;

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
        if (employeeRepository.existsById(scheduleRequest.getEmployeeId())) {
            EmployeeEntity employee =
                employeeRepository.findById(scheduleRequest.getEmployeeId())
                    .orElseThrow(() -> new EmployeeException(HttpStatus.BAD_REQUEST.toString(), EMPLOYEE_ALREADY_EXISTS));

            validateDay(scheduleRequest.getFirstDay());
            validateDay(scheduleRequest.getSecondDay());

            return plotSchedule(employee, scheduleRequest);
        } else {
            throw new EmployeeException(HttpStatus.BAD_REQUEST.toString(), EMPLOYEE_DOES_NOT_EXISTS);
        }
    }

    private ScheduleEntity plotSchedule(EmployeeEntity employee, ScheduleRequest scheduleRequest) {
        Optional<ScheduleEntity> existingSchedule =
            scheduleRepository.findByEmployeeId(employee.getId());

        if (existingSchedule.isPresent()) {
            existingSchedule.get().setFirstDay(scheduleRequest.getFirstDay());
            existingSchedule.get().setSecondDay(scheduleRequest.getSecondDay());
            return scheduleRepository.save(existingSchedule.get());
        } else {
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
