package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.entity.KyLuat;
import com.example.DoAnJava.repository.KyLuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KyLuatService {

    @Autowired
    private KyLuatRepository kiLuatRepository;

    public List<KyLuat> getAllKiLuats() {
        return kiLuatRepository.findAll();
    }

    public Optional<KyLuat> getKiLuatById(Long id) {
        return kiLuatRepository.findById(id);
    }

    public KyLuat addKyLuat(KyLuat kyLuat) {
        return kiLuatRepository.save(kyLuat);
    }

    public KyLuat UpdateKyLuat(KyLuat kyLuat) {
        return kiLuatRepository.save(kyLuat);
    }

    public void deleteKiLuat(Long id) {
        kiLuatRepository.deleteById(id);
    }

    public List<KyLuat> getKiLuatsByEmployeeId(Long employeeId) {
        return kiLuatRepository.findByEmployeeId(employeeId);
    }
}
