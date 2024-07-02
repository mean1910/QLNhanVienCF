package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.repository.KhenThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhenThuongService {

    @Autowired
    private KhenThuongRepository khenThuongRepository;

    public List<KhenThuong> getAllKhenThuongs() {
        return khenThuongRepository.findAll();
    }

    public Optional<KhenThuong> getKhenThuongById(Long id) {
        return khenThuongRepository.findById(id);
    }
    public KhenThuong addKhenThuong(KhenThuong khenThuong) {
        return khenThuongRepository.save(khenThuong);
    }

    public KhenThuong UpdateKhenThuong(KhenThuong khenThuong) {
        return khenThuongRepository.save(khenThuong);
    }

    public void deleteKhenThuong(Long id) {
        khenThuongRepository.deleteById(id);
    }

    public List<KhenThuong> getKhenThuongsByEmployeeId(Long employeeId) {
        return khenThuongRepository.findByEmployeeId(employeeId);
    }
}