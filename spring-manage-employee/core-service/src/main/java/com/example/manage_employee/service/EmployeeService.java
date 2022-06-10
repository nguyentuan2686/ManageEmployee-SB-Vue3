package com.example.manage_employee.service;

import com.example.manage_employee.dto.EmployeeDTO;
import com.example.manage_employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
    EmployeeDTO getEmployeeById(Integer id);
    void updateEmployee(Integer id, EmployeeDTO employeeDTO);
    void deleteEmployee(Integer id);

    List<EmployeeDTO> findAll();
}
