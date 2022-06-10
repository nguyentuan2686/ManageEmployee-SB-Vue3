package com.example.manage_employee.controller;


import com.example.manage_employee.dto.EmployeeDTO;
import com.example.manage_employee.dto.EmployeeLoginDTO;
import com.example.manage_employee.entity.Employee;
import com.example.manage_employee.exception.MyCustomException;
import com.example.manage_employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("get-by-id")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam(value = "id") Integer id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("get-all-employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                            @RequestParam(value = "sizePage", required = false) Integer sizePage,
                                                            @RequestParam(value = "sortField", required = false) String sortField,
                                                            @RequestParam(value = "sortType", required = false) String sortType) {
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee(keyword, currentPage, sizePage, sortField, sortType);
        return ResponseEntity.ok(employeeDTOList);
    }

    @PostMapping("add-employee")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("find-all")
    public ResponseEntity<List<EmployeeDTO>> getAll(){
        List<EmployeeDTO> employeeDTOList = employeeService.findAll();
        return ResponseEntity.ok(employeeDTOList);
    }

    @PutMapping("edit-employee/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> editEmployee(@PathVariable(value = "id") Integer id,
                                                    @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
//
//    }
}
