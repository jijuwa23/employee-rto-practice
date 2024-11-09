package com.ing.employeerto.employeerto.controller;

import com.ing.employeerto.employeerto.dto.EmployeeRequest;
import com.ing.employeerto.employeerto.dto.ScheduleRequest;
import com.ing.employeerto.employeerto.entity.EmployeeEntity;
import com.ing.employeerto.employeerto.entity.ScheduleEntity;
import com.ing.employeerto.employeerto.service.EmployeeRTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ing.employeerto.employeerto.common.EmployeeCommon.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1 + API)
public class EmployeeController {

    private final EmployeeRTOService employeeRTOService;

    @PostMapping(EMPLOYEE)
    ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeRTOService.addEmployee(employeeRequest));
    }

    @PostMapping(SCHEDULES + PLOT)
    ResponseEntity<ScheduleEntity> plotSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(employeeRTOService.plotSchedule(scheduleRequest));
    }

}
