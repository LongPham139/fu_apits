package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.RequestSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestSkillRepository extends JpaRepository<RequestSkill, Integer> {
}