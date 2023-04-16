package com.company.emsweb.controller;

import com.company.emsweb.entity.Employee;
import com.company.emsweb.exception.EmployeeNotFoundException;
import com.company.emsweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        List<Employee> listEmployees =  employeeService.listAll();
        model.addAttribute("listEmployees", listEmployees);
        return "employees";
    }

    @GetMapping("/employees/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Add New Employee");
        return "employee_form";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(Employee employee, RedirectAttributes ra) {
        employeeService.save(employee);
        ra.addFlashAttribute("message", "The employee has been saved successfully!");
        return "redirect:/employees";
    }

    @GetMapping("employees/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Employee employee =  employeeService.findById(id);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Edit Employee (Id: " + id + ")");
            return "employee_form";
        }
        catch (EmployeeNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/employees";
        }
    }

    @GetMapping("employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            employeeService.delete(id);
            ra.addFlashAttribute("message", "The employee Id " + id + " has been deleted!");
        }
        catch (EmployeeNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/employees";
    }
}
