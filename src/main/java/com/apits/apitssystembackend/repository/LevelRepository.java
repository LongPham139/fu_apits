package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    public Optional<Level> findLevelById(int id);
    public Optional<Level> findLevelByName(String name);
//    public List<Level> findLevelBySpecialtyId(int specialtyId);
    public List<Level> findAll();
//    @Query(nativeQuery = true, value = "SELECT id, name, specialty_id, status FROM aptis_db.level\n" +
//            "where specialty_id = :id And name = :name ;")
//    Level checkExist(@Param("name")String name, @Param("id") int id);
}
