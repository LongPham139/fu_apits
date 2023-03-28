package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.CandidateSpecialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSpecializeRepository extends JpaRepository<CandidateSpecialize, Integer> {
    List<CandidateSpecialize> findBySpecializeId(int specializeId);
}