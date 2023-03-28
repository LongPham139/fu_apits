package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CandidateSkillLevelCreateDTO;
import com.apits.apitssystembackend.constant.candidate.CandidateFailMessage;
import com.apits.apitssystembackend.constant.candidate_skill_level.CandidateSkillLevelErrorMessage;
import com.apits.apitssystembackend.constant.candidate_skill_level.CandidateSkillLevelStatus;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.constant.skill.SkillErrorMessage;
import com.apits.apitssystembackend.custom.SkillLevelCustom.SkillLevelCustomResponse;
import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.CandidateSkillLevel;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.CandidateRepository;
import com.apits.apitssystembackend.repository.CandidateSkillLevelRepository;
import com.apits.apitssystembackend.repository.LevelRepository;
import com.apits.apitssystembackend.repository.SkillRepository;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillLevelResponse;
import com.apits.apitssystembackend.response.CandidateSkillLevel.CandidateSkillsLevelsResponse;
import com.apits.apitssystembackend.service.CandidateSkillLevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateSkillLevelServiceImp implements CandidateSkillLevelService {
    private final CandidateSkillLevelRepository candidateSkillLevelRepository;
    private final CandidateRepository candidateRepository;
    private final SkillRepository skillRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Override
    public CandidateSkillLevelResponse findById(int id) {
        CandidateSkillLevel candidateSkillLevel = candidateSkillLevelRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(CandidateSkillLevelErrorMessage.CANDIDATE_SKILL_LEVEL_NOT_FOUND));
        return mapped(candidateSkillLevel);
    }

    @Override
    public CandidateSkillLevelResponse create(CandidateSkillLevelCreateDTO dto) {
        CandidateSkillLevel candidateSkillLevel = candidateSkillLevelRepository.checkExist(dto.getCandidateId(),
                dto.getSkillId(), dto.getLevelId());
        if (candidateSkillLevel == null) {
            Candidate candidate = candidateRepository.findCanById(dto.getCandidateId())
                    .orElseThrow(() -> new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND));
            Skill skill = skillRepository.findById(dto.getSkillId())
                    .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
            Level level = levelRepository.findLevelById(dto.getLevelId())
                    .orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
            candidateSkillLevel = CandidateSkillLevel
                    .builder()
                    .candidate(candidate)
                    .skill(skill)
                    .level(level)
                    .status(CandidateSkillLevelStatus.PROCESSING)
                    .build();
            candidateSkillLevelRepository.save(candidateSkillLevel);
        } else
            throw new ExistException(CandidateSkillLevelErrorMessage.CANDIDATE_SKILL_LEVEL_EXIST);
        return mapped(candidateSkillLevel);
    }

    @Override
    public CandidateSkillsLevelsResponse getListSkillByCandidateId(int id, String status) {
        CandidateSkillsLevelsResponse response = new CandidateSkillsLevelsResponse();
        List<CandidateSkillLevel> candidateSkillLevelList = candidateSkillLevelRepository.checkCandidateExist(id);
        if (!candidateSkillLevelList.isEmpty()) {
            List<SkillLevelCustomResponse> data = new ArrayList<>();
            List<Integer> listSkillId = candidateSkillLevelRepository.getListSkillByCandidateId(id, status);
            for (int skillId : listSkillId) {
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
                List<Level> listLevel = new ArrayList<>();
                List<Integer> listLevelId = candidateSkillLevelRepository.getListLevelByCandidateAndSkillId(id, skillId,
                        status);
                for (int levelId : listLevelId) {
                    Level level = levelRepository.findLevelById(levelId)
                            .orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
                    level.setStatus(status);
                    listLevel.add(level);
                }
                SkillLevelCustomResponse subResponse = SkillLevelCustomResponse
                        .builder()
                        .skillName(skill.getName())
                        .skillId(skill.getId())
                        .skillImage(skill.getImage())
                        .skillStatus(skill.getStatus())
                        .candidateLevelList(listLevel)
                        .build();
                data.add(subResponse);

                response.setCandidateSkills(data);

            }

        } else
            throw new NotFoundException(CandidateSkillLevelErrorMessage.CANDIDATE_SKILL_LEVEL_NOT_FOUND);
        return response;
    }

    private CandidateSkillLevelResponse mapped(CandidateSkillLevel data) {
        CandidateSkillLevelResponse response = new CandidateSkillLevelResponse();
        response.setId(data.getId());
        response.setCandidate(data.getCandidate());
        SkillLevelCustomResponse tmp = new SkillLevelCustomResponse();
        tmp.setSkillId(data.getSkill().getId());
        tmp.setSkillName(data.getSkill().getName());
        tmp.setSkillImage(data.getSkill().getImage());
        tmp.setSkillStatus(data.getSkill().getStatus());
        List<Level> levelList = new ArrayList<>();
        levelList.add(data.getLevel());
        tmp.setCandidateLevelList(levelList);
        response.setSkillLevelCustomResponse(tmp);
        response.setStatus(data.getStatus());
        return response;
    }
}
