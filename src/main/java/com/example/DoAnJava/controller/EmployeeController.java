package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.Employee;
import com.example.DoAnJava.services.EmployeeService;
import com.example.DoAnJava.services.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @GetMapping
    public String showAllEmployees(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "4") int size,
                                   @RequestParam(defaultValue = "") String search,
                                   @RequestParam(defaultValue = "") String position) {
        Page<Employee> employeePage;
        if (!search.isEmpty()) {
            employeePage = employeeService.searchEmployeesByName(search, page, size);
        } else if (!position.isEmpty()) {
            employeePage = employeeService.filterEmployeesByPosition(position, page, size);
        } else {
            employeePage = employeeService.getEmployees(page, size);
        }
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("search", search);
        model.addAttribute("position", position);
        model.addAttribute("positions", positionService.getAllPositions());
        return "employee/list";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", positionService.getAllPositions());
        return "employee/add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("positions", positionService.getAllPositions());
            return "employee/add";
        }
        if (employeeService.isEmailExistsForOtherEmployee(employee.getId(), employee.getEmail())) {
            result.rejectValue("email", "error.employee", "Email đã tồn tại.");
            model.addAttribute("positions", positionService.getAllPositions());
            return "employee/edit";
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") long id, Model model) {
        Employee editEmployee = employeeService.getEmployeeById(id).orElse(null);
        if (editEmployee != null) {
            model.addAttribute("employee", editEmployee);
            model.addAttribute("positions", positionService.getAllPositions());
            return "employee/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editEmployee(@Valid  @ModelAttribute("employee") Employee updateEmployee,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("positions", positionService.getAllPositions());
            return "employee/edit";
        }
        employeeService.updateEmployee(updateEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}