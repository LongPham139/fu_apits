package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.SkillCreateDTO;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.constant.skill.SkillErrorMessage;
import com.apits.apitssystembackend.constant.skill.SkillStatus;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.LevelRepository;
import com.apits.apitssystembackend.repository.SkillRepository;
import com.apits.apitssystembackend.response.LevelResponse;
import com.apits.apitssystembackend.response.SkillResponse;
import com.apits.apitssystembackend.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImp implements SkillService {
    private final SkillRepository skillRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<SkillResponse> getAllSkillPaging(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Skill> pageResult = skillRepository.findAll(pageable);
        List<SkillResponse> listSkill = new ArrayList<>();
        if(pageResult.hasContent()){
            for (Skill skill :
                    pageResult.getContent()) {
                SkillResponse skillResponse = modelMapper.map(skill, SkillResponse.class);
                listSkill.add(skillResponse);
            }
        }else throw new ListEmptyException(SkillErrorMessage.LIST_SKILL_EMPTY);
        return listSkill;
    }

    @Override
    public SkillResponse getSkillById(int id) {
        Skill skill = skillRepository.findById(id).orElseThrow(()-> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        SkillResponse skillResponse = modelMapper.map(skill, SkillResponse.class);
        return skillResponse;
    }

//    @Override
//    public List<SkillResponse> getSkillByLevelId(int levelId) {
//        Level level = levelRepository.findLevelById(levelId).orElseThrow(()->new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
//        List<SkillResponse> responseList = new ArrayList<>();
//        List<Skill> list = skillRepository.findSkillsByLevelId(level.getId());
//        if(list.size() > 0){
//            for (int i = 0; i < list.size(); i++) {
//               SkillResponse tmp = modelMapper.map(list.get(i), SkillResponse.class);
//               responseList.add(tmp);
//            }
//        }
//
//        return responseList;
//    }

    @Override
    public SkillResponse createSkill(SkillCreateDTO dto) {
        Skill skill = Skill.builder()
                .image(dto.getImage())
                .status(SkillStatus.ACTIVATE)
                .name(dto.getName())
                .build();
        skillRepository.save(skill);
        SkillResponse response = modelMapper.map(skill, SkillResponse.class);
        return response;
    }
}
