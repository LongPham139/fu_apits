package com.apits.apitssystembackend.repository;
import com.apits.apitssystembackend.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.apits.apitssystembackend.repository.query.CandidateQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findCandidateByEmail(String email);
    Optional<Candidate> findCanById(int id);

    Candidate findCandidateById(int id);

    @Query(nativeQuery = true, value = CandidateQuery.FIND_BY_IDS)
    List<Candidate> findByIds(@Param("ids") List<Integer> ids);
}
