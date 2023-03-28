package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.SpecializeSkill;
import com.apits.apitssystembackend.repository.query.SpecializeSkillQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializeSkillRepository extends JpaRepository<SpecializeSkill, Integer> {
    @Query(nativeQuery = true, value = SpecializeSkillQuery.FIND_BY_SKILL_IDS_AND_SIZE)
    List<SpecializeSkill> findBySkillIds(@Param("skillId") List<Integer> skillIds, @Param("size") int size);
}