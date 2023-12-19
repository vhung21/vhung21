package com.example.learncode5.repository;

import com.example.learncode5.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRespository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
