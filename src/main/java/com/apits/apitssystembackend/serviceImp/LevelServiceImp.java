package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.LevelCreateDTO;
import com.apits.apitssystembackend.constant.level.LevelErrorMessage;
import com.apits.apitssystembackend.constant.level.LevelStatus;
import com.apits.apitssystembackend.constant.specialty.SpecialtyErrorMessage;
import com.apits.apitssystembackend.entity.Level;
import com.apits.apitssystembackend.entity.Specialty;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.LevelRepository;
import com.apits.apitssystembackend.repository.SpecialtyRepository;
import com.apits.apitssystembackend.response.LevelResponse;
import com.apits.apitssystembackend.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelServiceImp implements LevelService {
    private final LevelRepository levelRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<LevelResponse> getAllLevelPaging(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Level> pageResult = levelRepository.findAll(pageable);
        List<LevelResponse> listLevel = new ArrayList<>();
        if(pageResult.hasContent()){
            for (Level level :
                    pageResult.getContent()) {
                LevelResponse levelResponse = modelMapper.map(level, LevelResponse.class);
                listLevel.add(levelResponse);
            }
        } else
            throw new ListEmptyException(LevelErrorMessage.LIST_LEVEL_EMPTY);
        return listLevel;
    }

    @Override
    public LevelResponse getLevelById(int id) {
        Level level = levelRepository.findLevelById(id).orElseThrow(()-> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
        return modelMapper.map(level, LevelResponse.class);
    }

//    @Override
//    public List<LevelResponse> getLevelBySpecialId(int specId) {
//        List<LevelResponse> levelResponses = new ArrayList<>();
//        List<Level> list = levelRepository.findLevelBySpecialtyId(specId);
//        if(list.size() > 0){
//            for (int i = 0; i < list.size(); i++) {
//                LevelResponse tmp = modelMapper.map(list.get(i), LevelResponse.class);
//                levelResponses.add(tmp);
//            }
//        }else throw new ListEmptyException(LevelErrorMessage.LIST_LEVEL_EMPTY);
//        return levelResponses;
//    }

    @Override
    public LevelResponse createLevel(LevelCreateDTO dto) {
        Optional<Level> level = levelRepository.findLevelByName(dto.getName());
        if(level.isEmpty()){
            Level tmp = Level
                    .builder()
                    .name(dto.getName())
                    .status(LevelStatus.ACTIVATE)
                    .build();
            levelRepository.save(tmp);
            return modelMapper.map(tmp, LevelResponse.class);
        }else {
            throw new ExistException(LevelErrorMessage.LEVEL_EXIST);
        }
    }

    @Override
    public LevelResponse disableLevel(int id) {
        Level level = levelRepository.findLevelById(id).orElseThrow(()-> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
        level.setStatus(LevelStatus.DISABLE);
        levelRepository.save(level);
        return modelMapper.map(level, LevelResponse.class);
    }

    @Override
    public LevelResponse enableLevel(int id) {
        Level level = levelRepository.findLevelById(id).orElseThrow(()-> new NotFoundException(LevelErrorMessage.LEVEL_NOT_FOUND));
        level.setStatus(LevelStatus.ACTIVATE);
        levelRepository.save(level);
        return modelMapper.map(level, LevelResponse.class);
    }
}
