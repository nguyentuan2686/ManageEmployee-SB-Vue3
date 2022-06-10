package com.example.manage_employee.service.impl;

import com.example.manage_employee.constant.FrontEndDefineCode;
import com.example.manage_employee.entity.Role;
import com.example.manage_employee.exception.MyCustomException;
import com.example.manage_employee.repo.RoleRepository;
import com.example.manage_employee.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        if(optionalRole.isPresent()){
            throw new MyCustomException("Role is already exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.ROLE_NAME_IS_ALREADY_EXIST);
        }
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isEmpty()){
            throw new MyCustomException("Role is not exist", HttpStatus.BAD_REQUEST, FrontEndDefineCode.ROLE_NAME_IS_ALREADY_EXIST);
        }
        return optionalRole.get();
    }



    @Override
    public void updateRole(Integer id) {

    }

    @Override
    public void deleteRole(Integer id) {

    }

    @Override
    public List<Role> getListRoleByListId(List<Integer> integerList) {
        Optional<List<Role>> optionalRoleList = roleRepository.findByListId(integerList);
        if(optionalRoleList.isEmpty()){
            throw new MyCustomException("There is error with list role", HttpStatus.BAD_REQUEST, FrontEndDefineCode.ERROR_UNKNOW);
        }
        return optionalRoleList.get();
    }
}
