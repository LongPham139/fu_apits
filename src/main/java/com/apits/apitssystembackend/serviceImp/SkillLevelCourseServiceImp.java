package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.SkillLevelCourseCreateDTO;
import com.apits.apitssystembackend.constant.course.CourseErrorMessage;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.constant.skill.SkillErrorMessage;
import com.apits.apitssystembackend.constant.skill_level.Skill_LevelErrorMessage;
import com.apits.apitssystembackend.entity.Course;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.entity.SkillLevelCourse;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.CourseRepository;
import com.apits.apitssystembackend.repository.LevelRepository;
import com.apits.apitssystembackend.repository.SkillRepository;
import com.apits.apitssystembackend.repository.SkillLevelCourseRepository;
import com.apits.apitssystembackend.custom.SpecialCustom.Level_CourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillCoursesResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillLevelsCourseResponse;
import com.apits.apitssystembackend.response.Skill_Level_Course.SkillsLevelCourseResponse;
import com.apits.apitssystembackend.service.SkillLevelCourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillLevelCourseServiceImp implements SkillLevelCourseService {
    private final SkillRepository skillRepository;
    private final LevelRepository levelRepository;
    private final SkillLevelCourseRepository skillLevelRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public SkillLevelCourseResponse createSkillLevel(SkillLevelCourseCreateDTO dto) {
        SkillLevelCourse skillLevelCourse = skillLevelRepository.checkExist(dto.getSkillId(), dto.getLevelId(), dto.getCourseId());
        if(skillLevelCourse == null){
            Skill skill = skillRepository.findById(dto.getSkillId())
                    .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
            Level level = levelRepository.findLevelById(dto.getLevelId())
                    .orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
            Course course = courseRepository.findCourseById(dto.getCourseId())
                    .orElseThrow(()->new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
            skillLevelCourse = SkillLevelCourse.builder()
                    .level(level)
                    .skill(skill)
                    .course(course)
                    .build();
            skillLevelRepository.save(skillLevelCourse);
            return modelMapper.map(skillLevelCourse, SkillLevelCourseResponse.class);
        }else {
            throw new ExistException(Skill_LevelErrorMessage.SKILL_LEVEL_EXIST);
        }

    }

    @Override
    public SkillLevelsCourseResponse getSkillLevelById(int id) {
        SkillLevelCourse skillLevelCourse = skillLevelRepository.getSkillLevelById(id)
                .orElseThrow(() -> new NotFoundException(Skill_LevelErrorMessage.NOT_FOUND));
        return mapped(skillLevelCourse);
    }

    @Override
    public SkillsLevelCourseResponse getListSkillByLevelId(int levelId) {
        SkillsLevelCourseResponse skillsLevelResponse = new SkillsLevelCourseResponse();
        Level level = levelRepository.findLevelById(levelId)
                .orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
        List<Skill> skillList = new ArrayList<>();
        List<SkillLevelCourse> list = skillLevelRepository.getListSkillByLevelId(levelId)
                .orElseThrow(() -> new ListEmptyException(Skill_LevelErrorMessage.LIST_IS_EMPTY));
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Skill tmp = list.get(i).getSkill();
                skillList.add(tmp);
            }

        }
        skillsLevelResponse.setSkillList(skillList);
        return skillsLevelResponse;
    }

    @Override
    public SkillLevelsCourseResponse getListLevelBySkillId(int skillId) {
        SkillLevelsCourseResponse response = new SkillLevelsCourseResponse();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        List<Integer> listLevels = skillLevelRepository.getListLevelBySkillId(skillId);
        List<Level_CourseResponse> levelCourseResponseList = new ArrayList<>();
        response.setSkillName(skill.getName());
        response.setSkillId(skill.getId());
        response.setSkillImage(skill.getImage());
        response.setStatus(skill.getStatus());
        for (int levelId: listLevels) {
            Level level = levelRepository.findLevelById(levelId).orElseThrow(() -> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
            List<Integer> listCourseId = skillLevelRepository.getCourseBySkillLevel(skillId, levelId);
            List<Course> listCourse = new ArrayList<>();
            Level_CourseResponse levelCourseResponse = new Level_CourseResponse();
            levelCourseResponse.setLevelId(levelId);
            levelCourseResponse.setLevelName(level.getName());
            levelCourseResponse.setLevelStatus(level.getStatus());
            for (int courseId : listCourseId) {
                Course course = courseRepository.findCourseById(courseId).orElseThrow(() -> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
                listCourse.add(course);
            }
            levelCourseResponse.setLevelCourses(listCourse);
            levelCourseResponseList.add(levelCourseResponse);
        }
        response.setSkillLevels(levelCourseResponseList);
        return response;
    }

    @Override
    public SkillCoursesResponse getListCourseBySkillId(int skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(()->new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        SkillCoursesResponse response = new SkillCoursesResponse();
        List<Integer> listCourseId = skillLevelRepository.getListCourseBySkillId(skillId);
        List<Course> courseList = new ArrayList<>();
        for (int courseId :
                listCourseId) {
            Course course = courseRepository.findCourseById(courseId).orElseThrow(()-> new NotFoundException(CourseErrorMessage.COURSE_NOT_FOUND));
            courseList.add(course);
        }
        response.setSkillId(skill.getId());
        response.setSkillName(skill.getName());
        response.setSkillImage(skill.getImage());
        response.setSkillStatus(skill.getStatus());
        response.setCourseList(courseList);
        return response;
    }

    private SkillLevelsCourseResponse mapped(SkillLevelCourse skillLevelCourse){
        SkillLevelsCourseResponse response = new SkillLevelsCourseResponse();
        List<Course> courseList = new ArrayList<>();
        courseList.add(skillLevelCourse.getCourse());
        List<Level_CourseResponse> levelCourseResponseList = new ArrayList<>();
        Level_CourseResponse levelCourseResponse = new Level_CourseResponse();
        levelCourseResponse.setLevelId(skillLevelCourse.getLevel().getId());
        levelCourseResponse.setLevelName(skillLevelCourse.getLevel().getName());
        levelCourseResponse.setLevelStatus(skillLevelCourse.getLevel().getStatus());
        levelCourseResponse.setLevelCourses(courseList);
        levelCourseResponseList.add(levelCourseResponse);
        response.setSkillId(skillLevelCourse.getSkill().getId());
        response.setSkillName(skillLevelCourse.getSkill().getName());
        response.setStatus(skillLevelCourse.getSkill().getStatus());
        response.setSkillImage(skillLevelCourse.getSkill().getImage());
        response.setSkillLevels(levelCourseResponseList);
        return response;
    }
}
