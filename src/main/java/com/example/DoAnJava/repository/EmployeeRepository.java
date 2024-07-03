package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Optional<Employee> findUserByEmail(String email);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE lower(e.firstName) LIKE lower(concat('%', :keyword, '%')) OR lower(e.lastName) LIKE lower(concat('%', :keyword, '%'))")
    Page<Employee> findByFirstNameOrLastName(@Param("keyword") String keyword, Pageable pageable);
//  Page<Employee> findByFirstNameOrLastName(String firstName, String lastName, Pageable pageable);

    Page<Employee> findByPosition_title(String title, Pageable pageable);
}
