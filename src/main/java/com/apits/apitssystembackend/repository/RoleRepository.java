package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public List<Role> findAll();

    public Role findByName(String name);

    public Role findById(int id);
}
