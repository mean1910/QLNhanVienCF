package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "BangLuong")
public class BangLuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nhanvien", nullable = false)
    private Employee employee;

    @Column(name = "NgayTinhLuong",nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngay;

    @Column(name = "SoNgayCong", nullable = false)
    private int ngayCong;

    @Column(name = "TienThuong")
    private Double tienThuong;

    @Column(name = "TienPhat")
    private Double tienPhat;

    @Column(name = "UngTruoc")
    private Double ungTruoc;

    @Column(name = "ThucLanh", nullable = false)
    private Double thucLanh;
}
