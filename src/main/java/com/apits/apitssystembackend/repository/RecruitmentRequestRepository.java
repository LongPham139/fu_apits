package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Enterprise;
import com.apits.apitssystembackend.entity.RecruitmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitmentRequestRepository extends JpaRepository<RecruitmentRequest, Integer> {
    Optional<RecruitmentRequest> findById(int id);
    Page<RecruitmentRequest> findByStatus(String status, Pageable pageable);
    Page<RecruitmentRequest> findByCreator(Enterprise enterprise, Pageable pageable);
}
