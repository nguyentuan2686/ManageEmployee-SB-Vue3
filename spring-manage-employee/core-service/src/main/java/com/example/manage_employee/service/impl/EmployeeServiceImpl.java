package com.example.manage_employee.service.impl;

import com.example.manage_employee.constant.FrontEndDefineCode;
import com.example.manage_employee.dto.EmployeeDTO;
import com.example.manage_employee.entity.Employee;
import com.example.manage_employee.entity.Role;
import com.example.manage_employee.exception.MyCustomException;
import com.example.manage_employee.repo.EmployeeRepository;
import com.example.manage_employee.service.EmployeeService;
import com.example.manage_employee.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final ModelMapper mapper;

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeNumber(employeeDTO.getEmployeeNumber());
        if (optionalEmployee.isPresent()) {
            throw new MyCustomException("Employee is already exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.EMPLOYEE_NUMBER_IS_ALREAdY_EXIST);
        }
        Employee employee = mapper.map(employeeDTO, Employee.class);
        List<Integer> listId = employeeDTO.getRoleListId();
        List<Role> roleList = roleService.getListRoleByListId(listId);
        employee.setRoleList(roleList);

        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {

        Page<Employee> employeePage;
        Sort sort;

        if(keyword == null){
            keyword = "";
        }

        if (currentPage == null) {
            currentPage = 0;
        }
        if (sizePage == null) {
            sizePage = 10;
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "firstName";
        }
        if (sortType == null || sortType.equalsIgnoreCase("DESC") || sortType.isEmpty()) {
            sortType = "DESC";
        }else{
            sortType = "ASC";
        }
        if(sortType.equalsIgnoreCase("DESC")){
            sort = Sort.by(sortField).descending();
        }else {
            sort = Sort.by(sortField).ascending();
        }

        Pageable pageable = PageRequest.of(currentPage, sizePage, sort);
        employeePage = employeeRepository.findAllByCreatia(keyword, pageable);
        List<Employee> employees = employeePage.getContent();
        List<EmployeeDTO> employeeDTOList = employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new MyCustomException("Employee is not exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.EMPLOYEE_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    public List<EmployeeDTO> findAll(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
        return employeeDTOList;
    }

    @Override
    public void updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new MyCustomException("The employee is not exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.EMPLOYEE_NOT_FOUND);
        }
        Employee employeeUpdate = mapper.map(employeeDTO, Employee.class);
        employeeRepository.save(employeeUpdate);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new MyCustomException("The employee is not exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.deleteById(id);
    }
}
