package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.SpecialtyCreateDTO;
import com.apits.apitssystembackend.constant.specialty.SpecialtyErrorMessage;
import com.apits.apitssystembackend.constant.specialty.SpecialtyStatus;
import com.apits.apitssystembackend.entity.Specialty;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.SpecialtyRepository;
import com.apits.apitssystembackend.response.SpecialtyResponse;
import com.apits.apitssystembackend.service.SpecialtyService;
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
public class SpecialtyServiceImp implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SpecialtyResponse> getAllSpecialty(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Specialty> pageResult = specialtyRepository.findAll(pageable);
        List<SpecialtyResponse> list = new ArrayList<>();
        if (pageResult.hasContent()) {
            for (Specialty specialty :
                    pageResult.getContent()) {
                SpecialtyResponse specialtyResponse = modelMapper.map(specialty, SpecialtyResponse.class);
                list.add(specialtyResponse);
            }
        } else throw new ListEmptyException(SpecialtyErrorMessage.LIST_SPECIALTY_IS_EMPTY);
        return list;
    }



    @Override
    public SpecialtyResponse getSpecialtyById(int id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        return modelMapper.map(specialty, SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse getSpecialtyByName(String name) {
        Specialty specialty = specialtyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        return modelMapper
                .map(specialty, SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse createSpecialty(SpecialtyCreateDTO dto) {
        SpecialtyResponse response = null;
        Optional<Specialty> category = specialtyRepository.findByName(dto.getName());
        if (category.isPresent())
            throw new ExistException(SpecialtyErrorMessage.SPECIALTY_EXIST);
        else {
            Specialty cate = Specialty.builder()
                    .name(dto.getName())
                    .status(SpecialtyStatus.ACTIVATED)
                    .build();
            specialtyRepository.save(cate);
            response = modelMapper.map(cate, SpecialtyResponse.class);
        }
        return response;
    }

    @Override
    public SpecialtyResponse disableSpecialty(int id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_EXIST));
        specialty.setStatus(SpecialtyStatus.DISABLED);
        specialtyRepository.save(specialty);
        return modelMapper.map(specialty, SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse activeSpecialty(int id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_EXIST));
        specialty.setStatus(SpecialtyStatus.ACTIVATED);
        specialtyRepository.save(specialty);
        return modelMapper.map(specialty, SpecialtyResponse.class);
    }
}
