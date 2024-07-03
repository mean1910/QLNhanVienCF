package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.repository.CaLamViecRepository;
import com.example.DoAnJava.repository.KhenThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaLamViecService {
    @Autowired
    private CaLamViecRepository caLamViecRepository;

    public List<CaLamViec> getAllCaLamViecs() {
        return caLamViecRepository.findAll();
    }
    public List<CaLamViec> getAllCaLamViecsOrderedByNgayDesc() {
        return caLamViecRepository.findAllByOrderByNgayDesc();
    }

    public Optional<CaLamViec> getCaLamViecById(Long id) {
        return caLamViecRepository.findById(id);
    }

    public CaLamViec addCaLamViec(CaLamViec caLamViec) {
        return caLamViecRepository.save(caLamViec);
    }

    public CaLamViec UpdateCaLamViec(CaLamViec caLamViec) {
        return caLamViecRepository.save(caLamViec);
    }

    public void deleteCaLamViec(Long id) {
        caLamViecRepository.deleteById(id);
    }

}
