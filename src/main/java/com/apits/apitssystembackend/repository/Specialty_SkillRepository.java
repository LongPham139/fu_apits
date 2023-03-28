package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.CandidateSpecialty;
import com.apits.apitssystembackend.entity.SpecializeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Specialty_SkillRepository extends JpaRepository<SpecializeSkill, Integer> {
    Optional<SpecializeSkill> getSpecializeSkillById(int id);
    @Query(nativeQuery = true, value =
            "SELECT id, skill_id, specialty_id " +
            "FROM aptis_db.specialty_skill " +
            "WHERE skill_id = :skillId ")
    Optional<List<SpecializeSkill>> getListSpecialtyBySkillId(@Param("skillId") int skillId);
    @Query(nativeQuery = true, value =
            "SELECT id, skill_id, specialty_id " +
            "FROM aptis_db.specialty_skill " +
            "WHERE specialty_id = :specialtyId ")
    Optional<List<SpecializeSkill>> getListSkillBySpecialtyId(@Param("specialtyId") int specialtyId);
    @Query(nativeQuery = true, value =
            "SELECT id, skill_id, specialty_id \n" +
            "FROM aptis_db.specialty_skill \n" +
            "WHERE specialty_id = :specialtyId AND skill_id = :skillId ")
    SpecializeSkill checkExist(@Param("skillId") int skillId,@Param("specialtyId") int specialtyId);
    @Query(nativeQuery = true, value =
            "SELECT distinct skill_id \n" +
                    "FROM aptis_db.specialty_skill \n" +
                    "WHERE specialty_id = ?1")
    List<Integer> getListSkillBySpecId(int specId);
}
