package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.CaLamViec;
import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.entity.KhenThuong;
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
@RequestMapping("/khenthuongs")
public class KhenThuongController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private KhenThuongService khenThuongService;

    @GetMapping
    public String getAllKhenThuongs(Model model) {
        List<KhenThuong> khenThuongs = khenThuongService.getAllKhenThuongs();
        model.addAttribute("khenThuongs", khenThuongs);
        return "khenthuong/list";
    }
    @GetMapping("/{employeeEmail}")
    public String getKhenThuongByEmployeeEmail(@PathVariable String employeeEmail, Model model) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeByEmail(employeeEmail);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            List<KhenThuong> khenThuongs = khenThuongService.getKhenThuongsByEmployeeId(employee.getId());
            model.addAttribute("khenThuongs", khenThuongs);
            return "khenThuong/list";
        } else {
            // Handle the case where the employee is not found
            model.addAttribute("errorMessage", "Employee not found with email: " + employeeEmail);
            return "error";
        }
    }
    @GetMapping("/add")
    public String addKhenThuongForm(Model model) {
        KhenThuong khenthuong = new KhenThuong();
        khenthuong.setNgay(new Date());
        model.addAttribute("khenthuong", khenthuong);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "khenthuong/add";
    }

    @PostMapping("/add")
    public String addKhenThuong(@Valid @ModelAttribute("khenthuong") KhenThuong khenthuong, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "khenthuong/add";
        }
        khenThuongService.addKhenThuong(khenthuong);
        return "redirect:/khenthuongs";
    }
    @GetMapping("/edit/{id}")
    public String editKhenThuongForm(@PathVariable("id") long id, Model model) {
        KhenThuong editkhenthuong = khenThuongService.getKhenThuongById(id).orElse(null);
        if (editkhenthuong != null) {
            model.addAttribute("khenthuong", editkhenthuong);
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "khenthuong/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editKhenThuong(@Valid @ModelAttribute("khenthuong") KhenThuong updateKhenThuong, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "khenthuong/edit";
        }
        khenThuongService.UpdateKhenThuong(updateKhenThuong);
        return "redirect:/khenthuongs";
    }

    @GetMapping("/delete/{id}")
    public String deleteKhenthuong(@PathVariable("id") long id) {
        khenThuongService.deleteKhenThuong(id);
        return "redirect:/khenthuongs";
    }
}
