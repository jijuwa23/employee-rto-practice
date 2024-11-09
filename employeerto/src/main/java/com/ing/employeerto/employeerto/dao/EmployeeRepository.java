package com.ing.employeerto.employeerto.dao;

import com.ing.employeerto.employeerto.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    Optional<EmployeeEntity> findByName(String name);
}
