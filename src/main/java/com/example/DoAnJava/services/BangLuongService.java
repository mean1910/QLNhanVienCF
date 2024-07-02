package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.BangLuong;
import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.repository.BangLuongRepository;
import com.example.DoAnJava.repository.ChiTietCaLamViecRepository;
import com.example.DoAnJava.repository.KhenThuongRepository;
import com.example.DoAnJava.repository.KyLuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BangLuongService {
    @Autowired
    private BangLuongRepository bangLuongRepository;

    @Autowired
    private ChiTietCaLamViecRepository chiTietCaLamViecRepository;

    @Autowired
    private KyLuatRepository kyLuatRepository;

    @Autowired
    private KhenThuongRepository khenThuongRepository;

    public List<BangLuong> getAllBangLuongs() {
        return bangLuongRepository.findAll();
    }
    public List<BangLuong> getBangLuongByEmployeeId(Long employeeId) {
        return bangLuongRepository.findByEmployeeId(employeeId);
    }

    public Optional<BangLuong> getBanLuongById(Long id) {
        return bangLuongRepository.findById(id);
    }

    public BangLuong addBangLuong(BangLuong bangLuong) {
        return bangLuongRepository.save(bangLuong);
    }

    public BangLuong UpdateBangLuong(BangLuong bangLuong) {
        return bangLuongRepository.save(bangLuong);
    }

    public void deleteBangLuong(Long id) {
        bangLuongRepository.deleteById(id);
    }

    public List<BangLuong> getBangLuongsByEmployeeId(Long employeeId) {
        return bangLuongRepository.findByEmployeeId(employeeId);
    }

    public BangLuong calculateBangLuong(Employee employee, Date ngayTinhLuong, Double ungTruoc) {
        int month = ngayTinhLuong.getMonth() +1;
        int year = ngayTinhLuong.getYear() + 1900;

        int soCaLamViec = chiTietCaLamViecRepository.countByEmployeeIdAndMonthAndYear(employee.getId(), month, year);
        int soCaNgayLe = chiTietCaLamViecRepository.countHolidayShiftsByEmployeeIdAndMonthAndYear(employee.getId(), month, year);
        soCaLamViec += soCaNgayLe * 3; // Tính 4 lần cho các ca ngày lễ

        Double soTienKyLuat = Optional.ofNullable(kyLuatRepository.sumKyLuatByEmployeeIdAndMonthAndYear(employee.getId(), month, year)).orElse(0.0);
        Double soTienKhenThuong = Optional.ofNullable(khenThuongRepository.sumKhenThuongByEmployeeIdAndMonthAndYear(employee.getId(), month, year)).orElse(0.0);

        Double thucLanh = (employee.getSalary() * soCaLamViec) - soTienKyLuat + soTienKhenThuong - ungTruoc;

        BangLuong bangLuong = new BangLuong();
        bangLuong.setEmployee(employee);
        bangLuong.setNgay(ngayTinhLuong);
        bangLuong.setNgayCong(soCaLamViec);
        bangLuong.setTienThuong(soTienKhenThuong);
        bangLuong.setTienPhat(soTienKyLuat);
        bangLuong.setUngTruoc(ungTruoc);
        bangLuong.setThucLanh(thucLanh);

        return bangLuongRepository.save(bangLuong);
    }
}
