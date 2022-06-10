package com.example.manage_employee.repo;

import com.example.manage_employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT e from Employee e where ?1 is null or e.firstName like %?1% or e.firstName like %?1%")
    Page<Employee> findAllByCreatia(String keyword, Pageable pageable);

    Optional<Employee> findByEmployeeNumber(String employeeNumber);

}
