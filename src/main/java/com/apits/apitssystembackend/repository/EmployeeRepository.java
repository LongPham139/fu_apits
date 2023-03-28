package com.apits.apitssystembackend.repository;
import com.apits.apitssystembackend.entity.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findById(int id);

    public boolean existsByEmployeeCode(String code);

    boolean existsByPhone(String phone);

    public Optional<Employee> findByPhone(String phone);

}
