package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.ChiTietCaLamViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietCaLamViecRepository extends JpaRepository<ChiTietCaLamViec, Long> {
    List<ChiTietCaLamViec> findByCaLamViecId(Long idCaLamViec);
    Optional<ChiTietCaLamViec> findByEmployeeIdAndCaLamViecId(Long employeeId, Long calamviecId);
    @Query("SELECT COUNT(c) FROM ChiTietCaLamViec c WHERE c.employee.id = :employeeId AND FUNCTION('MONTH', c.caLamViec.ngay) = :month AND FUNCTION('YEAR', c.caLamViec.ngay) = :year")
    int countByEmployeeIdAndMonthAndYear(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);
    @Query("SELECT COUNT(c) FROM ChiTietCaLamViec c WHERE c.employee.id = :employeeId AND FUNCTION('MONTH', c.caLamViec.ngay) = :month AND FUNCTION('YEAR', c.caLamViec.ngay) = :year AND c.caLamViec.loaiCa = 'Ca Ngày Lễ'")
    int countHolidayShiftsByEmployeeIdAndMonthAndYear(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);
    @Query("SELECT c.caLamViec FROM ChiTietCaLamViec c WHERE c.employee.id = :employeeId")
    List<CaLamViec> findCaLamViecsByEmployeeId(@Param("employeeId") Long employeeId);
}
