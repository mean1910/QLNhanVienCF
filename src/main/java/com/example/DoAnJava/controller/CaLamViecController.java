package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.ChiTietCaLamViec;
import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.services.CaLamViecService;
import com.example.DoAnJava.services.ChiTietCaLamViecService;
import com.example.DoAnJava.services.EmployeeService;
import com.example.DoAnJava.services.KhenThuongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/calamviecs")
public class CaLamViecController {
    @Autowired
    private CaLamViecService caLamViecService;
    @Autowired
    private ChiTietCaLamViecService chiTietCaLamViecService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllCaLamViecs(Model model) {
        List<CaLamViec> caLamViecs = caLamViecService.getAllCaLamViecs();
        model.addAttribute("caLamViecs", caLamViecs);
        return "calamviec/list";
    }
    @GetMapping("/{employeeEmail}")
    public String getCaLamViecsByEmployeeEmail(@PathVariable String employeeEmail, Model model) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeByEmail(employeeEmail);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            List<CaLamViec> caLamViecs = employeeService.getCaLamViecsByEmployeeId(employee.getId());
            model.addAttribute("caLamViecs", caLamViecs);
            return "calamviec/list";
        } else {
            // Handle the case where the employee is not found
            model.addAttribute("errorMessage", "Employee not found with email: " + employeeEmail);
            return "error";
        }
    }
    @GetMapping("/add")
    public String addCaLamViecForm(Model model) {
        CaLamViec caLamViec = new CaLamViec();
        caLamViec.setSoNhanVien(8.0);
        model.addAttribute("calamviec", caLamViec);
        return "calamviec/add";
    }

    @PostMapping("/add")
    public String addCaLamViec(@Valid @ModelAttribute("calamviec") CaLamViec calamviec, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "calamviec/add";
        }
        caLamViecService.addCaLamViec(calamviec);
        return "redirect:/calamviecs";
    }
    @GetMapping("/edit/{id}")
    public String editCaLamViecForm(@PathVariable("id") long id, Model model) {
        CaLamViec editcalamviec = caLamViecService.getCaLamViecById(id).orElse(null);
        if (editcalamviec != null) {
            model.addAttribute("calamviec", editcalamviec);
            return "calamviec/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editCaLamViec(@Valid @ModelAttribute("calamviec") CaLamViec updateCaLamViec, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "calamviec/edit";
        }
        caLamViecService.UpdateCaLamViec(updateCaLamViec);
        return "redirect:/calamviecs";
    }

    @GetMapping("/delete/{id}")
    public String deleteCaLamViec(@PathVariable("id") long id) {
        caLamViecService.deleteCaLamViec(id);
        return "redirect:/calamviecs";
    }

    @GetMapping("/chitiet/{id}")
    public String ChiTietCaLamViecs(@PathVariable("id") long id,Model model) {
        List<ChiTietCaLamViec> chiTietCaLamViecs = chiTietCaLamViecService.getChiTietCaLamViecsByCaLamViecId(id);
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("chiTietCaLamViec", new ChiTietCaLamViec());
        model.addAttribute("calamviecId", id);
        model.addAttribute("chiTietCaLamViecs", chiTietCaLamViecs);
        return "calamviec/chitiet";
    }
    @PostMapping("/chitiet/add")
    public String addChiTietCaLamViec(@ModelAttribute ChiTietCaLamViec chiTietCaLamViec, @RequestParam Long calamviecId, Model model) {
        Long employeeId = chiTietCaLamViec.getEmployee().getId();
        if (chiTietCaLamViecService.findByEmployeeIdAndCaLamViecId(employeeId, calamviecId).isPresent()) {
            List<Employee> employees = employeeService.getAllEmployees();
            List<ChiTietCaLamViec> chiTietCaLamViecs = chiTietCaLamViecService.getChiTietCaLamViecsByCaLamViecId(calamviecId);
            model.addAttribute("employees", employees);
            model.addAttribute("chiTietCaLamViec", new ChiTietCaLamViec());
            model.addAttribute("chiTietCaLamViecs", chiTietCaLamViecs);
            model.addAttribute("calamviecId", calamviecId);
            model.addAttribute("errorMessage", "Nhân viên này đã có trong ca làm việc.");
            return "calamviec/chitiet";
        }
        CaLamViec caLamViec = caLamViecService.getCaLamViecById(calamviecId).orElse(null);
        chiTietCaLamViec.setCaLamViec(caLamViec);
        chiTietCaLamViecService.createChiTietCaLamViec(chiTietCaLamViec);
        return "redirect:/calamviecs/chitiet/" + calamviecId;
    }
    @GetMapping("/deletenv/{id}")
    public String deleteNhanVienCaLamViec(@PathVariable("id") long id,@RequestParam("calamviecId") Long calamviecId) {
        chiTietCaLamViecService.deleteChiTietCaLamViec(id);
        return "redirect:/calamviecs/chitiet/" + calamviecId;
    }
}
