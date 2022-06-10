package com.example.manage_employee.controller;

import com.example.manage_employee.constant.FrontEndDefineCode;
import com.example.manage_employee.entity.Role;
import com.example.manage_employee.exception.MyCustomException;
import com.example.manage_employee.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable(value = "id") Integer id){
        Role role = roleService.getRoleById(id);
        if(role == null){
            throw new MyCustomException("Role not found", HttpStatus.BAD_REQUEST, FrontEndDefineCode.ROLE_NOT_FOUND);
        }
        return ResponseEntity.ok(role);
    }


    @PostMapping
    public ResponseEntity<String> addRole(@RequestBody Role role){
        Integer arr[] = {1,2,3};
        List<Integer> list = Arrays.asList(arr);
        roleService.addRole(role);
        return ResponseEntity.status(HttpStatus.OK).body("Add role success");
    }

    @GetMapping("list-id/{id}")
    public ResponseEntity<List<Role>> getListRole(@PathVariable Integer[] id){
        List<Integer> list = Arrays.asList(id);
        List<Role> roleList = roleService.getListRoleByListId(list);
        return ResponseEntity.ok(roleList);
    }
}
