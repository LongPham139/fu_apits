package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    public List<Specialty> findAll();
    public Optional<Specialty> findById(int specialtyId);
    public Optional<Specialty> findByName(String name);
    Specialty getByName(String name);
}
