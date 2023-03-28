package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Position;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findById(int id);
    Optional<Position> findByName(String name);

    @Query(nativeQuery = true, value = "" +
            "select count(e.position_id) as total " +
            "from employee e " +
            "where e.position_id = :positionId")
    int countUsedPosition(@Param("positionId") int positionId);
}
