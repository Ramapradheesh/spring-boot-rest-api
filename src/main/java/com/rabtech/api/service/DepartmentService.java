package com.rabtech.api.service;

import com.rabtech.api.entity.Department;
import com.rabtech.api.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department createDepartment(Department department) {
        return repository.save(department);
    }

    public Department updateDepartment(Long id, Department updated) {
        Department department = getDepartmentById(id);
        department.setName(updated.getName());
        department.setDescription(updated.getDescription());
        return repository.save(department);
    }

    public void deleteDepartment(Long id) {
        repository.delete(getDepartmentById(id));
    }

    public List<Department> searchDepartments(String name) {
        return repository.searchByName(name);
    }
}