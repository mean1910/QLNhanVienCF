package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "CaLamViec")
public class CaLamViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LoaiCa",nullable = false)
    private String loaiCa;

    @Column(name = "NgayLamViec",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngay;

    @Column(name = "BuoiCa",nullable = false)
    private String buoiCa;

    @Column(name = "SoNhanVien",nullable = false)
    private Double soNhanVien;

    @OneToMany(mappedBy = "caLamViec", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChiTietCaLamViec> chiTietCaLamViecs;
}
