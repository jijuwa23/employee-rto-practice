package com.ing.employeerto.employeerto.dao;

import com.ing.employeerto.employeerto.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
    Optional<ScheduleEntity> findByEmployeeId(Long id);
}
