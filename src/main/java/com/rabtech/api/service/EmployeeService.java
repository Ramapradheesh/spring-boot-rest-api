package com.rabtech.api.service;

import com.rabtech.api.entity.Employee;
import com.rabtech.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updated) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(updated.getFirstName());
        employee.setLastName(updated.getLastName());
        employee.setEmail(updated.getEmail());
        employee.setSalary(updated.getSalary());
        employee.setDepartment(updated.getDepartment());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.delete(getEmployeeById(id));
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }

    public List<Employee> searchEmployees(String name) {
        return repository.searchByName(name);
    }

    public List<Employee> getEmployeesByMinimumSalary(Double salary) {
        return repository.findEmployeesWithMinimumSalary(salary);
    }
}