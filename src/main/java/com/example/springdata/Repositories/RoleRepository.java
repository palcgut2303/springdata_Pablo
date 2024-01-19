package com.example.springdata.Repositories;

import com.example.springdata.entity.Role;
import com.example.springdata.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
