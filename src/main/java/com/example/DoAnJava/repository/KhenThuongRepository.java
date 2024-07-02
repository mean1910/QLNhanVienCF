package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.KhenThuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhenThuongRepository extends JpaRepository<KhenThuong, Long> {
    List<KhenThuong> findByEmployeeId(Long employeeId);
    @Query("SELECT SUM(k.soTien) FROM KhenThuong k WHERE k.employee.id = :employeeId AND FUNCTION('MONTH', k.ngay) = :month AND FUNCTION('YEAR', k.ngay) = :year")
    Double sumKhenThuongByEmployeeIdAndMonthAndYear(Long employeeId, int month, int year);
}
