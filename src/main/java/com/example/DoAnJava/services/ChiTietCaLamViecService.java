package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.ChiTietCaLamViec;
import com.example.DoAnJava.repository.ChiTietCaLamViecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietCaLamViecService {
    @Autowired
    private ChiTietCaLamViecRepository chiTietCaLamViecRepository;

    public List<ChiTietCaLamViec> getAllChiTietCaLamViecs() {
        return chiTietCaLamViecRepository.findAll();
    }

    public ChiTietCaLamViec createChiTietCaLamViec(ChiTietCaLamViec chiTietCaLamViec) {
        return chiTietCaLamViecRepository.save(chiTietCaLamViec);
    }
    public void deleteChiTietCaLamViec(Long id) {
        chiTietCaLamViecRepository.deleteById(id);
    }

    public List<ChiTietCaLamViec> getChiTietCaLamViecsByCaLamViecId(Long idCaLamViec) {
        return chiTietCaLamViecRepository.findByCaLamViecId(idCaLamViec);
    }
    public Optional<ChiTietCaLamViec> findByEmployeeIdAndCaLamViecId(Long employeeId, Long calamviecId) {
        return chiTietCaLamViecRepository.findByEmployeeIdAndCaLamViecId(employeeId, calamviecId);
    }
}
