package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.LevelCreateDTO;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.response.LevelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {
    List<LevelResponse> getAllLevelPaging(int pageNo, int pageSize);
    LevelResponse getLevelById(int id);
//    List<LevelResponse> getLevelBySpecialId(int specId);
    LevelResponse createLevel(LevelCreateDTO dto);
    LevelResponse disableLevel(int id);
    LevelResponse enableLevel(int id);
}
