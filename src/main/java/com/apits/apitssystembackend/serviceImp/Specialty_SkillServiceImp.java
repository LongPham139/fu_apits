package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.Specialty_SkillCreateDTO;
import com.apits.apitssystembackend.constant.skill.SkillErrorMessage;
import com.apits.apitssystembackend.constant.special_skill.Special_SkillErrorMessage;
import com.apits.apitssystembackend.constant.specialty.SpecialtyErrorMessage;
import com.apits.apitssystembackend.entity.Skill;
import com.apits.apitssystembackend.entity.SpecializeSkill;
import com.apits.apitssystembackend.entity.Specialty;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.SkillRepository;
import com.apits.apitssystembackend.repository.SpecialtyRepository;
import com.apits.apitssystembackend.repository.Specialty_SkillRepository;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtiesSkillResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.SpecialtySkillsResponse;
import com.apits.apitssystembackend.response.Specialty_Skill.Specialty_SkillResponse;
import com.apits.apitssystembackend.service.Specialty_SkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Specialty_SkillServiceImp implements Specialty_SkillService {
    private final Specialty_SkillRepository specialtySkillRepository;
    private final SpecialtyRepository specialtyRepository;
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    @Override
    public Specialty_SkillResponse createSpecializeSkill(Specialty_SkillCreateDTO dto) {
        SpecializeSkill specializeSkill = specialtySkillRepository.checkExist(dto.getSkillId(), dto.getSpecialtyId());
        if(specializeSkill == null){
            Specialty_SkillResponse specialtySkillResponse = null;
            Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                    .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
            Skill skill = skillRepository.findById(dto.getSkillId())
                    .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
            specializeSkill = SpecializeSkill.builder()
                    .skill(skill)
                    .specialty(specialty)
                    .build();
            specialtySkillRepository.save(specializeSkill);
            specialtySkillResponse = modelMapper.map(specializeSkill, Specialty_SkillResponse.class);
            return specialtySkillResponse;
        }else{
            throw new ExistException(Special_SkillErrorMessage.SPECIAL_SKILL_EXIST);
        }
    }

    @Override
    public Specialty_SkillResponse getSpecializeSkillById(int id) {
        Specialty_SkillResponse specialtySkillResponse = new Specialty_SkillResponse();
        SpecializeSkill specializeSkill = specialtySkillRepository.getSpecializeSkillById(id)
                .orElseThrow(() -> new NotFoundException(Special_SkillErrorMessage.NOT_FOUND));
        specialtySkillResponse = modelMapper.map(specializeSkill, Specialty_SkillResponse.class);
        return specialtySkillResponse;
    }

    @Override
    public SpecialtiesSkillResponse getListSpecBySkillId(int skillId) {
        SpecialtiesSkillResponse response = new SpecialtiesSkillResponse();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException(SkillErrorMessage.SKILL_NOT_FOUND));
        List<Specialty> specialtyList = new ArrayList<>();
        List<SpecializeSkill> list = specialtySkillRepository.getListSpecialtyBySkillId(skillId)
                .orElseThrow(() -> new ListEmptyException(Special_SkillErrorMessage.LIST_IS_EMPTY));
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Specialty tmp = list.get(i).getSpecialty();
                specialtyList.add(tmp);
            }
            response.setSkill(skill);
            response.setSpecialtyList(specialtyList);
        }
        return response;
    }

    @Override
    public SpecialtySkillsResponse getListSkillBySpecicalId(int specId) {
        SpecialtySkillsResponse response = new SpecialtySkillsResponse();
        Specialty specialty = specialtyRepository.findById(specId)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        List<Skill> skillList = new ArrayList<>();
        List<SpecializeSkill> list = specialtySkillRepository.getListSkillBySpecialtyId(specId)
                .orElseThrow(() -> new ListEmptyException(Special_SkillErrorMessage.LIST_IS_EMPTY));
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Skill tmp = list.get(i).getSkill();
                skillList.add(tmp);
            }
            response.setSpecialty(specialty);
            response.setSkillList(skillList);
        }
        return response;
    }


}
