package com.apits.apitssystembackend.repository.query;

public class CandidateQuery {
    public static final String SELECT = "SELECT * FROM `candidate` c";
    public static final String WHERE = " WHERE ";
    public static final String FIND_BY_IDS = SELECT + WHERE + " c.id IN (:ids)";
}