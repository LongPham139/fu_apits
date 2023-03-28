package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.CandidateSpecialty;
import com.apits.apitssystembackend.entity.SpecializeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Candidate_SpecialtyRepository extends JpaRepository<CandidateSpecialty, Integer> {
    Optional<CandidateSpecialty> getCandidateSpecialtiesById(int id);
    @Query(nativeQuery = true, value = "SELECT id, status, candidate_id, specialty_id \n" +
            "FROM aptis_db.candidate_specialty \n" +
            "where candidate_id = :candidateId")
    Optional<List<CandidateSpecialty>> getCandidateSpecialtyByCandidateId(@Param("candidateId") int candidateId);
    @Query(nativeQuery = true, value = "SELECT id, status, candidate_id, specialty_id \n" +
            "FROM aptis_db.candidate_specialty \n" +
            "where specialty_id = :specialtyId")
    Optional<List<CandidateSpecialty>> getListCandidateBySpecialId(@Param("specialtyId") int specialtyId);
    @Query(nativeQuery = true, value =
            "SELECT id, candidate_id, specialty_id, status \n" +
                    "FROM aptis_db.candidate_specialty \n" +
                    "WHERE candidate_id = :candidate_id AND specialty_id = :specialty_id ")
    CandidateSpecialty checkExist(@Param("candidate_id") int candidate_id, @Param("specialty_id") int specialty_id);
    @Query(nativeQuery = true, value =
            "SELECT specialty_id \n" +
                    "FROM aptis_db.candidate_specialty \n" +
                    "WHERE candidate_id = :candidate_id "
    )
    List<Integer> getListSpecByCandidateId(@Param("candidate_id") int candidate_id);
}
