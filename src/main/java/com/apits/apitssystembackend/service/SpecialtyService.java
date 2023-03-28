package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.SpecialtyCreateDTO;
import com.apits.apitssystembackend.response.SpecialtyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {
    List<SpecialtyResponse> getAllSpecialty(int pageNo, int pageSize);
    SpecialtyResponse getSpecialtyById(int id);
    SpecialtyResponse getSpecialtyByName(String name);
    SpecialtyResponse createSpecialty(SpecialtyCreateDTO dto);
    SpecialtyResponse disableSpecialty(int id);
    SpecialtyResponse activeSpecialty(int id);
}
