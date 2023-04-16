package com.company.emsweb.service.impl;

import com.company.emsweb.entity.Employee;
import com.company.emsweb.exception.EmployeeNotFoundException;
import com.company.emsweb.repository.EmployeeRepository;
import com.company.emsweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public void save(Employee employee) {
       employeeRepository.save(employee);
    }

    public Employee findById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> result =  employeeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Employee not found " + id);
    }

    public void delete(Long id) {
        Long count = employeeRepository.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Employee not found " + id);
        }
        employeeRepository.deleteById(id);
    }
}
