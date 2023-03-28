package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.constant.candidate.CandidateFailMessage;
import com.apits.apitssystembackend.constant.course.CourseErrorMessage;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.constant.skill.SkillErrorMessage;
import com.apits.apitssystembackend.constant.specialty.SpecialtyErrorMessage;
import com.apits.apitssystembackend.custom.CandidateCustom.CandidateCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Skill_LevelsCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.SpecialCustomResponse;
import com.apits.apitssystembackend.custom.SpecialCustom.Special_SkillsCustomResponse;
import com.apits.apitssystembackend.entity.*;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.*;
import com.apits.apitssystembackend.custom.SpecialCustom.Level_CourseResponse;
import com.apits.apitssystembackend.service.CustomResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomResponseServiceImp implements CustomResponseService {
    private final CandidateRepository candidateRepository;
    private final Candidate_SpecialtyRepository candidateSpecialtyRepository;
    private final Specialty_SkillRepository specialtySkillRepository;
    private final SkillLevelCourseRepository skillLevelRepository;
    private final SpecialtyRepository specialtyRepository;
    private final CourseRepository courseRepository;
    private final SkillRepository skillRepository;
    private final LevelRepository levelRepository;

    @Override
    public CandidateCustomResponse getListSpecSkillLevelCourseByCandidateId(int id, String status) {
        CandidateCustomResponse response = new CandidateCustomResponse();
        Candidate candidate = candidateRepository.findCanById(id)
                .orElseThrow(() -> new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND));
        response.setCandidate(candidate);
        List<Special_SkillsCustomResponse> specialSkillsCustomResponseList = new ArrayList<>();
        List<Skill_LevelsCustomResponse> skillLevelsCustomResponseList = new ArrayList<>();
        List<Level_CourseResponse> levelCourseResponseList = new ArrayList<>();
        List<Integer> listSpecId = candidateSpecialtyRepository.getListSpecByCandidateId(id);
        if (listSpecId.isEmpty())
            throw new ListEmptyException(SpecialtyErrorMessage.LIST_SPECIALTY_IS_EMPTY);
//        List<Integer> listSkillId = candidateSkillRepository.getListSkillByCandidateId(id, status);
//        List<Integer> listLevelID = candidateLevelRepository.getListLevelByCandidateId(id, status);
        List<Skill_LevelsCustomResponse> skillLevelsCustomResponses = new ArrayList<>();
        Special_SkillsCustomResponse specialSkillsCustomResponse;
        for (int specId : listSpecId) {
            Specialty specialty = specialtyRepository.findById(specId)
                    .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
            specialSkillsCustomResponse = Special_SkillsCustomResponse
                    .builder()
                    .specialty(specialty)
                    .build();
            // Special_SkillsCustomResponse specSkillRes = getSkillsBySpecial(specId,
            // listSkillId, listLevelID);
            // specialSkillsCustomResponseList.add(specSkillRes);
            specialSkillsCustomResponseList.add(specialSkillsCustomResponse);

        }
        response.setListSpecial(specialSkillsCustomResponseList);
        return response;
    }

    // DÙNG ĐỂ TẠO LIST SPECIAL -> SKILL -> LEVEL -> COURSE CÓ SẴN TRONG DB
    // BEGIN
    @Override
    public SpecialCustomResponse getSpecialCustomBySpecId(int specId) {
        Specialty specialty = specialtyRepository.findById(specId)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        SpecialCustomResponse response = new SpecialCustomResponse();
        Special_SkillsCustomResponse customResponse = getSkillsBySpecial(specialty.getId());
        List<Special_SkillsCustomResponse> list = new ArrayList<>();
        list.add(customResponse);
        response.setListSpecial(list);
        return response;
    }

    @Override
    public Level_CourseResponse getListCourseByLevelId(int levelId) {
        Level_CourseResponse levelCourseCustomResponse = new Level_CourseResponse();
        Level level = levelRepository.findLevelById(levelId)
                .orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
        levelCourseCustomResponse.setLevelId(level.getId());
        levelCourseCustomResponse.setLevelName(level.getName());
        levelCourseCustomResponse.setLevelStatus(level.getStatus());
        List<Integer> listCoursesId = skillLevelRepository.getListCourseByLevelId(levelId);
        List<Course> courseList = new ArrayList<>();
        for (int courseId : listCoursesId) {
            Course course = courseRepository.findCourseById(courseId)
                    .orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
            courseList.add(course);
        }
        levelCourseCustomResponse.setLevelCourses(courseList);
        return levelCourseCustomResponse;
    }

    @Override
    public Skill_LevelsCustomResponse getLevelBySkillId(int skillId) {
        Skill_LevelsCustomResponse customResponse = new Skill_LevelsCustomResponse();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        customResponse.setSkill(skill);
        List<Integer> listLevelId = skillLevelRepository.getListLevelBySkillId(skillId);
        List<Level_CourseResponse> levelCourseResponseList = new ArrayList<>();
        for (int levelId : listLevelId) {
            Level_CourseResponse levelCourseResponse = getListCourseByLevelId(levelId);
            levelCourseResponseList.add(levelCourseResponse);
        }
        customResponse.setSkillLevels(levelCourseResponseList);
        return customResponse;
    }

    @Override
    public Special_SkillsCustomResponse getSkillsBySpecial(int specialId) {
        Special_SkillsCustomResponse customResponse = new Special_SkillsCustomResponse();
        Specialty specialty = specialtyRepository.findById(specialId)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        customResponse.setSpecialty(specialty);
        List<Integer> listSkill = specialtySkillRepository.getListSkillBySpecId(specialId);
        List<Skill_LevelsCustomResponse> skillLevelsCustomResponses = new ArrayList<>();
        for (int skillId : listSkill) {
            Skill_LevelsCustomResponse skillLevelsCustomResponse = getLevelBySkillId(skillId);
            skillLevelsCustomResponses.add(skillLevelsCustomResponse);
        }
        customResponse.setSpecialSkills(skillLevelsCustomResponses);
        return customResponse;
    }

    @Override
    public List<Special_SkillsCustomResponse> getAllSpecial() {
        List<Special_SkillsCustomResponse> specialSkillsCustomResponseList = new ArrayList<>();
        List<Specialty> specialtyList = specialtyRepository.findAll();
        if (specialtyList.isEmpty()) {
            throw new ListEmptyException(SpecialtyErrorMessage.LIST_SPECIALTY_IS_EMPTY);
        } else {
            for (int i = 0; i < specialtyList.size(); i++) {
                Special_SkillsCustomResponse customResponse = getSkillsBySpecial(specialtyList.get(i).getId());
                specialSkillsCustomResponseList.add(customResponse);
            }
        }
        return specialSkillsCustomResponseList;
    }

    // END
    @Override
    public Skill_LevelsCustomResponse getLevelBySkillId(int skillId, List<Integer> listlevel) {
        Skill_LevelsCustomResponse customResponse = new Skill_LevelsCustomResponse();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        customResponse.setSkill(skill);
        List<Level_CourseResponse> levelCourseResponseList = new ArrayList<>();
        for (int levelId : listlevel) {
            Level_CourseResponse levelCourseResponse = getListCourseByLevelId(levelId);
            levelCourseResponseList.add(levelCourseResponse);
        }
        customResponse.setSkillLevels(levelCourseResponseList);
        return customResponse;
        // TODO Auto-generated method stub
    }

    @Override
    public Special_SkillsCustomResponse getSkillsBySpecial(int specialId, List<Integer> listSkill,
            List<Integer> listLevel) {
        Special_SkillsCustomResponse customResponse = new Special_SkillsCustomResponse();
        Specialty specialty = specialtyRepository.findById(specialId)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        customResponse.setSpecialty(specialty);
        List<Skill_LevelsCustomResponse> skillLevelsCustomResponses = new ArrayList<>();
        for (int skillId : listSkill) {
            Skill_LevelsCustomResponse skillLevelsCustomResponse = getLevelBySkillId(skillId, listLevel);
            skillLevelsCustomResponses.add(skillLevelsCustomResponse);
        }
        customResponse.setSpecialSkills(skillLevelsCustomResponses);
        return customResponse;
    }

}
