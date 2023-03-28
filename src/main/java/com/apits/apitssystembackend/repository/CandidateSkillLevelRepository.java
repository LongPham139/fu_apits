package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.CandidateSkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CandidateSkillLevelRepository extends JpaRepository<CandidateSkillLevel, Integer> {
    Optional<CandidateSkillLevel> findById(int id);
    @Query(nativeQuery = true, value =
            "SELECT id, candidate_id, skill_id,level_id, status \n" +
            "FROM aptis_db.candidate_skill_level \n" +
            "Where candidate_id = ?1 and skill_id = ?2 and level_id = ?3 ")
    CandidateSkillLevel checkExist(int candidateId, int skillId, int levelId);
    @Query(nativeQuery = true, value =
            "SELECT distinct skill_id \n" +
            "FROM aptis_db.candidate_skill_level \n" +
            "WHERE candidate_id = ?1 and status LIKE %?2")
    List<Integer> getListSkillByCandidateId(int id, String status);
    @Query(nativeQuery = true, value =
            "SELECT distinct level_id\n" +
            "FROM aptis_db.candidate_skill_level \n" +
            "WHERE candidate_id = ?1 and skill_id = ?2 and status LIKE %?3")
    List<Integer> getListLevelByCandidateAndSkillId(int candidateId, int skillId, String status);
    @Query(nativeQuery = true, value =
            "SELECT distinct id, candidate_id, skill_id, level_id, status \n" +
            "FROM aptis_db.candidate_skill_level \n" +
            "WHERE candidate_id = ?1 ")
    List<CandidateSkillLevel> checkCandidateExist(int candidateId);

}
