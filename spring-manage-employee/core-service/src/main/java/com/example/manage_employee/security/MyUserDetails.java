package com.example.manage_employee.security;

import com.example.manage_employee.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author TuanNA
 * @Date 18/01/2022 11:11 PM
 * @Version 1.0
 */
public class MyUserDetails implements UserDetails {

    private final List<? extends GrantedAuthority> authorities;
    private final String username;
    private final String password;

    public MyUserDetails(Employee employee) {
        this.authorities = employee.getRoleList().stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        this.username = employee.getEmployeeNumber();
        this.password = employee.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}