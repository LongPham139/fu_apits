package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
    public Optional<Enterprise> getById(int id);

}
