package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.entity.KhenThuong;
import com.example.DoAnJava.entity.KyLuat;
import com.example.DoAnJava.services.EmployeeService;
import com.example.DoAnJava.services.KhenThuongService;
import com.example.DoAnJava.services.KyLuatService;
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
@RequestMapping("/kyluats")
public class KyLuatController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private KyLuatService kyLuatService;

    @GetMapping
    public String getAllKhenThuongs(Model model) {
        List<KyLuat> kyLuats = kyLuatService.getAllKiLuats();
        model.addAttribute("kyLuats", kyLuats);
        return "kyluat/list";
    }
    @GetMapping("/{employeeEmail}")
    public String getKyLuatsByEmployeeEmail(@PathVariable String employeeEmail, Model model) {
        Optional<Employee> employeeOpt = employeeService.getEmployeeByEmail(employeeEmail);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            List<KyLuat> kyLuats = kyLuatService.getKiLuatsByEmployeeId(employee.getId());
            model.addAttribute("kyLuats", kyLuats);
            return "kyluat/list";
        } else {
            // Handle the case where the employee is not found
            model.addAttribute("errorMessage", "Employee not found with email: " + employeeEmail);
            return "error";
        }
    }
    @GetMapping("/add")
    public String addKyLuatForm(Model model) {
        KyLuat kyLuat = new KyLuat();
        kyLuat.setNgay(new Date());
        model.addAttribute("kyluat", kyLuat);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "kyluat/add";
    }

    @PostMapping("/add")
    public String addKyLuat(@Valid @ModelAttribute("kyluat") KyLuat kyLuat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "kyluat/add";
        }
        kyLuatService.addKyLuat(kyLuat);
        return "redirect:/kyluats";
    }
    @GetMapping("/edit/{id}")
    public String editKyLuatForm(@PathVariable("id") long id, Model model) {
        KyLuat editkyluat = kyLuatService.getKiLuatById(id).orElse(null);
        if (editkyluat != null) {
            model.addAttribute("kyluat", editkyluat);
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "kyluat/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editKyLuat(@Valid @ModelAttribute("kyluat") KyLuat updateKyLuat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "kyluat/edit";
        }
        kyLuatService.UpdateKyLuat(updateKyLuat);
        return "redirect:/kyluats";
    }

    @GetMapping("/delete/{id}")
    public String deleteKyLuat(@PathVariable("id") long id) {
        kyLuatService.deleteKiLuat(id);
        return "redirect:/kyluats";
    }
}
