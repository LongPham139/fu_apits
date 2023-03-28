package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.SkillLevelCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillLevelCourseRepository extends JpaRepository<SkillLevelCourse, Integer> {
    Optional<SkillLevelCourse> getSkillLevelById(int id);

    @Query(nativeQuery = true, value =
            "SELECT id, skill_id, level_id, course_id " +
                    "FROM aptis_db.skill_level_course " +
                    "WHERE level_id = :levelId ")
    Optional<List<SkillLevelCourse>> getListSkillByLevelId(@Param("levelId") int levelId);

    @Query(nativeQuery = true, value =
            "SELECT distinct level_id \n" +
                    " FROM aptis_db.skill_level_course \n" +
                    " WHERE skill_id = :skillId \n")
    List<Integer> getListLevelBySkillId(@Param("skillId") int skillId);

    @Query(nativeQuery = true, value =
            "SELECT distinct course_id \n" +
                    " FROM aptis_db.skill_level_course \n" +
                    " WHERE skill_id = ?1")
    List<Integer> getListCourseBySkillId(int skill_id);

    @Query(nativeQuery = true, value =
            "SELECT id, skill_id, level_id, course_id \n" +
                    "FROM aptis_db.skill_level_course \n" +
                    "WHERE skill_id = :skill_id AND level_id = :level_id AND course_id= :course_id")
    SkillLevelCourse checkExist(@Param("skill_id") int skill_id, @Param("level_id") int level_id, @Param("course_id") int course_id);
    @Query(nativeQuery = true, value = "SELECT distinct course_id \n" +
            "FROM aptis_db.skill_level_course \n " +
            "WHERE level_id = ?1")
    List<Integer> getListCourseByLevelId(int levelId);
    @Query(nativeQuery = true, value =
            "SELECT course_id , skill_id, level_id \n" +
                    "FROM aptis_db.skill_level_course \n" +
                    "WHERE skill_id = ?1 and level_id = ?2;")
    List<Integer> getCourseIdBySkillLevelId(int skillId, int levelId);
    @Query(nativeQuery = true, value = "SELECT course_id " +
            "FROM aptis_db.skill_level_course " +
            "Where skill_id = ?1 AND level_id = ?2 ")
    List<Integer> getCourseBySkillLevel(int skillId, int levelId);

}
