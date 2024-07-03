package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.CaLamViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaLamViecRepository extends JpaRepository<CaLamViec, Long> {
    List<CaLamViec> findAllByOrderByNgayDesc();
}
