package com.apits.apitssystembackend.repository.query;

public class SpecializeSkillQuery {
    public static final String FIND_BY_SKILL_IDS_AND_SIZE =
            "SELECT *, COUNT(ss.specialize_id) AS size, IF(size > :size) " +
            "FROM `specialize_skill` ss " +
            "WHERE ss.skill_id IN (:skillIds)";
}