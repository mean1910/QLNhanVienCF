package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.BangLuong;
import com.example.DoAnJava.entity.KhenThuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BangLuongRepository extends JpaRepository<BangLuong, Long> {
    List<BangLuong> findByEmployeeId(Long employeeId);
}
