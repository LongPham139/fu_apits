package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.CanSpecCreateDTO;
import com.apits.apitssystembackend.constant.candidate.CandidateFailMessage;
import com.apits.apitssystembackend.constant.canspec.CanSpecErrorMessage;
import com.apits.apitssystembackend.constant.canspec.CanSpecStatus;
import com.apits.apitssystembackend.constant.specialty.SpecialtyErrorMessage;
import com.apits.apitssystembackend.entity.Candidate;
import com.apits.apitssystembackend.entity.CandidateSpecialty;
import com.apits.apitssystembackend.entity.Specialty;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.Candidate_SpecialtyRepository;
import com.apits.apitssystembackend.repository.CandidateRepository;
import com.apits.apitssystembackend.repository.SpecialtyRepository;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CanSpecsResponse;
import com.apits.apitssystembackend.response.Candidate_Specialty.CansSpecResponse;
import com.apits.apitssystembackend.service.CandidateSpecialtyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanSpecServiceImp implements CandidateSpecialtyService {
    private final CandidateRepository candidateRepository;
    private final SpecialtyRepository specialtyRepository;
    private final Candidate_SpecialtyRepository candidateSpecialtyRepository;
    private final ModelMapper modelMapper;

    @Override
    public CanSpecResponse getCanSpecById(int id) {
        CandidateSpecialty candidateSpecialty = candidateSpecialtyRepository.getCandidateSpecialtiesById(id)
                .orElseThrow(() -> new NotFoundException(CanSpecErrorMessage.NOT_FOUND));
        return modelMapper.map(candidateSpecialty, CanSpecResponse.class);
    }

    @Override
    public CanSpecResponse create(CanSpecCreateDTO dto) {
        CandidateSpecialty candidateSpecial = candidateSpecialtyRepository.checkExist(dto.getCandidateId(), dto.getSpecialId());
        CanSpecResponse canSpecResponse = null;
        if(candidateSpecial == null){
            Specialty specialty = specialtyRepository.findById(dto.getSpecialId())
                    .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
            Candidate candidate = candidateRepository.findCandidateById(dto.getCandidateId());
            if (candidate != null) {
                candidateSpecial = CandidateSpecialty.builder()
                        .candidate(candidate)
                        .specialty(specialty)
                        .status(CanSpecStatus.ACTIVE)
                        .build();
                candidateSpecialtyRepository.save(candidateSpecial);
                canSpecResponse = modelMapper.map(candidateSpecial, CanSpecResponse.class);
            } else
                throw new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND);
            return canSpecResponse;
        }else {
            throw new ExistException(CanSpecErrorMessage.EXIST);
        }
    }

    @Override
    public CanSpecsResponse getSpecialByCanId(int candidateId) {
        CanSpecsResponse canSpecsResponse = new CanSpecsResponse();
        Candidate candidate = candidateRepository.findCanById(candidateId)
                .orElseThrow(() -> new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND));
        List<Specialty> specialtyList = new ArrayList<>();
        List<CandidateSpecialty> list = candidateSpecialtyRepository.getCandidateSpecialtyByCandidateId(candidateId)
                .orElseThrow(()->new ListEmptyException(CanSpecErrorMessage.LIST_IS_EMPTY));
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Specialty tmp = list.get(i).getSpecialty();
                specialtyList.add(tmp);
            }
            canSpecsResponse.setCandidateSpecialList(specialtyList);
        } else throw new ListEmptyException(CanSpecErrorMessage.LIST_IS_EMPTY);
        return canSpecsResponse;
    }

    @Override
    public CansSpecResponse getCandidatesBySpecialId(int specialId) {
        CansSpecResponse cansSpecResponse = new CansSpecResponse();
        Specialty specialty = specialtyRepository.findById(specialId)
                .orElseThrow(() -> new NotFoundException(SpecialtyErrorMessage.SPECIALTY_NOT_FOUND));
        List<Candidate> list = new ArrayList<>();
        List<CandidateSpecialty> candidateSpecialtyList = candidateSpecialtyRepository.getListCandidateBySpecialId(specialId)
                .orElseThrow(()->new ListEmptyException(CanSpecErrorMessage.LIST_IS_EMPTY));
        if (candidateSpecialtyList.size() > 0) {
            for (int i = 0; i < candidateSpecialtyList.size(); i++) {
                Candidate tmp = candidateSpecialtyList.get(i).getCandidate();
                list.add(tmp);
            }
            cansSpecResponse.setCandidateList(list);
            cansSpecResponse.setSpecialty(specialty);
        } else throw new ListEmptyException(CanSpecErrorMessage.LIST_IS_EMPTY);
        return cansSpecResponse;
    }
}
