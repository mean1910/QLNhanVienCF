package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
