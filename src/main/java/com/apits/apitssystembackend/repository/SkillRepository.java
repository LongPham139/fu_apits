package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.apits.apitssystembackend.repository.query.SkillQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    public List<Skill> findAll();

    public Optional<Skill> findById(int id);

    public Optional<Skill> findByName(String name);

    @Query(nativeQuery = true, value = SkillQuery.FIND_BY_IDS)
    List<Skill> findBySkillIds(@Param("ids") List<Integer> ids);

//    List<Skill> findSkillsByLevelId(int levelId);
//    @Query(nativeQuery = true, value = "")
//    Skill checkExist(String name, int id);
}
