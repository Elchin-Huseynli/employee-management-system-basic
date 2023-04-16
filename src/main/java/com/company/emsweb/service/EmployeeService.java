package com.company.emsweb.service;

import com.company.emsweb.entity.Employee;
import java.util.List;

public interface EmployeeService {
    public List<Employee> listAll();

    public void save(Employee employee);

    public Employee findById(Long id);

    public void delete(Long id);
}
