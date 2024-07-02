package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.KyLuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KyLuatRepository extends JpaRepository<KyLuat, Long> {
    List<KyLuat> findByEmployeeId(Long employeeId);
    @Query("SELECT SUM(k.soTienPhat) FROM KyLuat k WHERE k.employee.id = :employeeId AND FUNCTION('MONTH', k.ngay) = :month AND FUNCTION('YEAR', k.ngay) = :year")
    Double sumKyLuatByEmployeeIdAndMonthAndYear(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);
}
