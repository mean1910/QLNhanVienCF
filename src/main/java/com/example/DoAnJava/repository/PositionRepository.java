package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
