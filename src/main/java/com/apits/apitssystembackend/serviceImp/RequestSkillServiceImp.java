package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.entity.RequestSkill;
import com.apits.apitssystembackend.repository.RequestSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestSkillServiceImp {
    private final RequestSkillRepository requestSkillRepository;
    public RequestSkillServiceImp(RequestSkillRepository requestSkillRepository) {
        this.requestSkillRepository = requestSkillRepository;
    }
    public boolean saveAll(List<RequestSkill> requestSkills) {
        try {
            requestSkillRepository.saveAll(requestSkills);
            return true;
        } catch (Exception e) {
            System.out.println("RequestSkillServiceImpl | saveAll error: " + e.getMessage());
            return false;
        }
    }
}