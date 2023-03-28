package com.apits.apitssystembackend.repository.query;

public class SkillQuery {
    public static final String SELECT = "SELECT * FROM `skill` s";
    public static final String WHERE = " WHERE ";
    public static final String FIND_BY_IDS = SELECT + WHERE + " s.id IN (:ids)";
}