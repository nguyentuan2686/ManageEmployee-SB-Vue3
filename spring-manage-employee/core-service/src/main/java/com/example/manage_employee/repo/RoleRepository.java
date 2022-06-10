package com.example.manage_employee.repo;

import com.example.manage_employee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query(value = "select r from Role r where r.id in :ids")
    Optional<List<Role>> findByListId(@Param("ids") List<Integer> integerList);
}
