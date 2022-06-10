package com.example.manage_employee.service;

import com.example.manage_employee.entity.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    Role getRoleById(Integer id);
    void updateRole(Integer id);
    void deleteRole(Integer id);

    List<Role> getListRoleByListId(List<Integer> integerList);

}
