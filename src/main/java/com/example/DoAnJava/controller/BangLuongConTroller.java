package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.BangLuong;
import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.services.BangLuongService;
import com.example.DoAnJava.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bangluongs")
public class BangLuongConTroller {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BangLuongService bangLuongService;
    @GetMapping
    public String getAllBangLuongs(Model model) {
        List<BangLuong> bangLuongs = bangLuongService.getAllBangLuongs();
        model.addAttribute("bangLuongs", bangLuongs);
        return "bangluong/list";
    }
    @GetMapping("/{employeeEmail}")
    public String getBangLuongByEmployeeEmail(@PathVariable String employeeEmail, Model model) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeByEmail(employeeEmail);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            List<BangLuong> bangLuongs = bangLuongService.getBangLuongByEmployeeId(employee.getId());
            model.addAttribute("bangLuongs", bangLuongs);
            return "bangluong/list";
        } else {
            // Handle the case where the employee is not found
            model.addAttribute("errorMessage", "Employee not found with email: " + employeeEmail);
            return "error";
        }
    }
    @GetMapping("/tinhluong")
    public String showCalculateForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "bangluong/tinhluong";
    }

    @PostMapping("/tinhluong")
    public String calculateBangLuong(@RequestParam Long employeeId,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTinhLuong,
                                     @RequestParam Double ungTruoc,
                                     Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + employeeId));
        BangLuong bangLuong = bangLuongService.calculateBangLuong(employee, ngayTinhLuong, ungTruoc);
        model.addAttribute("bangLuong", bangLuong);
        return "redirect:/bangluongs";
    }
    @GetMapping("/delete/{id}")
    public String deleteCaLamViec(@PathVariable("id") long id) {
        bangLuongService.deleteBangLuong(id);
        return "redirect:/bangluongs";
    }
}
