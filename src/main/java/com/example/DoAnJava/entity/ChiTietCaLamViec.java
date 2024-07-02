package com.example.DoAnJava.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ChiTietCaLamViec")
public class ChiTietCaLamViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nhanvien", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_calamviec", nullable = false)
    private CaLamViec caLamViec;
}
