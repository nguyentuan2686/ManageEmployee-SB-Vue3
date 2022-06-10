package com.example.manage_employee.dto;

import com.example.manage_employee.constant.WorkStatus;
import com.example.manage_employee.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private int id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private Integer age;
    private WorkStatus workStatus;
    private List<Role> roleList;
    private String password;
    private List<Integer> roleListId;
}
