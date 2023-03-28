package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.constant.candidate.CandidateFailMessage;
import com.apits.apitssystembackend.constant.employee.EmployeeErrorMessage;
import com.apits.apitssystembackend.constant.assign.AssignStatus;
import com.apits.apitssystembackend.constant.assign.AssignErrorMessage;
import com.apits.apitssystembackend.constant.recruitment_request.RecruitmentRequestErrorMessage;
import com.apits.apitssystembackend.entity.*;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.CandidateRepository;
import com.apits.apitssystembackend.repository.EmployeeRepository;
import com.apits.apitssystembackend.repository.AssignRepository;
import com.apits.apitssystembackend.repository.RecruitmentRequestRepository;
import com.apits.apitssystembackend.response.AssignCandidateResponse;
import com.apits.apitssystembackend.response.AssignResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.service.AssignService;
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
public class AssignServiceImp implements AssignService {

    private final AssignRepository assignRepository;
    private final RecruitmentRequestRepository recruitmentRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Override
    public AssignResponse getAssignById(int id) {
        Assign assign = assignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AssignErrorMessage.ASSIGN_NOT_FOUND));
        AssignResponse response = modelMapper.map(assign, AssignResponse.class);
        return response;
    }
    @Override
    public ResponseWithTotalPage<AssignResponse> getAllAssign(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Assign> pageResult = assignRepository.findAll(pageable);
        List<AssignResponse> list = new ArrayList<>();
        ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Assign assign : pageResult.getContent()) {
                AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                list.add(assignResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
        return result;
    }

    public ResponseWithTotalPage<AssignResponse> getAssignByEmployee(int employeeId, int pageNo, int pageSize){
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
            Page<Assign> pageResult = assignRepository.findByHumanResource(employee.getId(), pageable);
            List<AssignResponse> list = new ArrayList<AssignResponse>();
            ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
            if (pageResult.hasContent()) {
                for (Assign assign : pageResult.getContent()) {
                    AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                    list.add(assignResponse);
                }
                result.setResponseList(list);
                result.setTotalPage(pageResult.getTotalPages());
            } else
                throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
            return result;

    }
    public ResponseWithTotalPage<AssignResponse> getAllAssignByRecruitmentRequest(int requestId, int pageNo, int pageSize){
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(RecruitmentRequestErrorMessage.RECRUITMENT_REQUEST_NOT_FOUND_EXCEPTION));
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Assign> pageResult = assignRepository.findByRecruitmentRequest(recruitmentRequest, pageable);
        List<AssignResponse> list = new ArrayList<AssignResponse>();
        ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Assign assign : pageResult.getContent()) {
                AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                list.add(assignResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
        return result;
    }
    public ResponseWithTotalPage<AssignResponse> getAllPendingAssign(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Assign> pageResult = assignRepository.findByStatus(AssignStatus.PENDING, pageable);
        List<AssignResponse> list = new ArrayList<AssignResponse>();
        ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Assign assign : pageResult.getContent()) {
                AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                list.add(assignResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
        return result;
    }
    public ResponseWithTotalPage<AssignResponse> getAllApprovedAssign(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Assign> pageResult = assignRepository.findByStatus(AssignStatus.APPROVED, pageable);
        List<AssignResponse> list = new ArrayList<AssignResponse>();
        ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Assign assign : pageResult.getContent()) {
                AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                list.add(assignResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
        return result;
    }
    public ResponseWithTotalPage<AssignResponse> getAllCancelAssign(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Assign> pageResult = assignRepository.findByStatus(AssignStatus.CANCELED, pageable);
        List<AssignResponse> list = new ArrayList<AssignResponse>();
        ResponseWithTotalPage<AssignResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Assign assign : pageResult.getContent()) {
                AssignResponse assignResponse = modelMapper.map(assign, AssignResponse.class);
                list.add(assignResponse);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(AssignErrorMessage.LIST_IS_EMPTY);
        return result;

    }
    public AssignResponse cancelAssign(int id, int employeeId){
        Assign assign = assignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AssignErrorMessage.ASSIGN_NOT_FOUND));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(EmployeeErrorMessage.EMPLOYEE_NOT_FOUND_EXCEPTION));
        assign.setAssigner(employee);
        assign.setStatus(AssignStatus.CANCELED);

        Assign assignSaved = assignRepository.save(assign);

        AssignResponse response = modelMapper.map(assignSaved, AssignResponse.class);
        return response;
    }

    public AssignCandidateResponse approvedAssign(int id, int candidateId){
        Assign assign = assignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AssignErrorMessage.ASSIGN_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND));
        assign.setCandidate(candidate);
        assign.setStatus(AssignStatus.APPROVED);
        Assign assignSaved = assignRepository.save(assign);

        AssignCandidateResponse response = modelMapper.map(assignSaved, AssignCandidateResponse.class);
        return response;
    }
    public AssignCandidateResponse rejectedAssign(int id, int candidateId){
        Assign assign = assignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AssignErrorMessage.ASSIGN_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException(CandidateFailMessage.CANDIDATE_NOT_FOUND));
        assign.setCandidate(candidate);
        assign.setStatus(AssignStatus.REJECTED);
        Assign assignSaved = assignRepository.save(assign);

        AssignCandidateResponse response = modelMapper.map(assignSaved, AssignCandidateResponse.class);
        return response;
    }
}
