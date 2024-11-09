package com.ing.employeerto.employeerto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule_table")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id",unique = true,nullable = false)
    private EmployeeEntity employee;

    @Column(name = "firstDay")
    private String firstDay;

    @Column(name = "secondDay")
    private String secondDay;
}
