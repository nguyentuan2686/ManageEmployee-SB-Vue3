package com.example.manage_employee.service.impl;

import com.example.manage_employee.constant.FrontEndDefineCode;
import com.example.manage_employee.entity.Employee;
import com.example.manage_employee.exception.MyCustomException;
import com.example.manage_employee.repo.EmployeeRepository;
import com.example.manage_employee.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeNumber(username);
        optionalEmployee.orElseThrow(() -> new MyCustomException("Can't find employee", HttpStatus.BAD_REQUEST, FrontEndDefineCode.EMPLOYEE_NOT_FOUND));
        UserDetails userDetails = new MyUserDetails(optionalEmployee.get());
        redisTemplate.opsForValue().set("User", optionalEmployee.get());
        return userDetails;
    }
}
