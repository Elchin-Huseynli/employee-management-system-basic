package com.company.emsweb.repository;

import com.company.emsweb.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Long countById(Long id);
}
