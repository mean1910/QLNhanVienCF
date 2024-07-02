package com.example.DoAnJava.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "KyLuat")
public class KyLuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "LyDo",nullable = false)
    private String lyDo;

    @Column(name = "NgayQuyetDinh",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngay;

    @Column(name = "SoTienPhat",nullable = false)
    private Double soTienPhat;
}
