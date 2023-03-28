package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Assign;
import com.apits.apitssystembackend.entity.RecruitmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignRepository extends JpaRepository<Assign, Integer> {
    public Optional<Assign> findById(int id);
    @Query(nativeQuery = true, value = "select * from assign where employee_id = :id")
    public Page<Assign> findByHumanResource(@Param("id") int employeeId, Pageable pageable);
    public Page<Assign> findByStatus(String status, Pageable pageable);
    public Page<Assign> findByRecruitmentRequest(RecruitmentRequest recruitmentRequest, Pageable pageable);
}
