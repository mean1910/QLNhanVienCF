package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.repository.ChiTietCaLamViecRepository;
import com.example.DoAnJava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ChiTietCaLamViecRepository chiTietCaLamViecRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findUserByEmail(email);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public boolean isEmailExistsForOtherEmployee(Long id, String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return employee != null && !employee.getId().equals(id);
    }
    public List<CaLamViec> getCaLamViecsByEmployeeId(Long employeeId) {
        return chiTietCaLamViecRepository.findCaLamViecsByEmployeeId(employeeId);
    }
}
