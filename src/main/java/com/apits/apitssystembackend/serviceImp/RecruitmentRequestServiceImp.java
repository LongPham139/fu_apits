package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.*;
import com.apits.apitssystembackend.constant.enterprise.EnterpriseErrorMessage;
import com.apits.apitssystembackend.constant.recruitment_request.RecruitmentRequestErrorMessage;
import com.apits.apitssystembackend.constant.recruitment_request.RecruitmentRequestStatus;
import com.apits.apitssystembackend.entity.Enterprise;
import com.apits.apitssystembackend.entity.RecruitmentRequest;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.EnterpriseRepository;
import com.apits.apitssystembackend.repository.RecruitmentRequestRepository;
import com.apits.apitssystembackend.response.RecruitmentRequestResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.apits.apitssystembackend.entity.RequestSkill;
import java.util.Objects;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentRequestServiceImp{
    private final RecruitmentRequestRepository recruitmentRequestRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ModelMapper modelMapper;
    private final RequestSkillServiceImp requestSkillService;
    private static final String THOA_THUAN = "Negotiable";
    public ResponseWithTotalPage<RecruitmentRequestResponse> getAllRecruitmentRequests(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<RecruitmentRequest> pageResult = recruitmentRequestRepository.findAll(pageable);
        List<RecruitmentRequestResponse> list = new ArrayList<RecruitmentRequestResponse>();
        ResponseWithTotalPage<RecruitmentRequestResponse> result = new ResponseWithTotalPage<>();

        if (pageResult.hasContent()) {
            for (RecruitmentRequest recruitmentRequest : pageResult.getContent()) {
                RecruitmentRequestResponse response = modelMapper.map(recruitmentRequest,
                        RecruitmentRequestResponse.class);
                if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
                    response.setSalaryDetail(
                            (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
                } else if (recruitmentRequest.getSalaryFrom() == null
                        && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
                } else if (recruitmentRequest.getSalaryTo() == null
                        && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
                } else
                    response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
                list.add(response);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(RecruitmentRequestErrorMessage.LIST_RECRUITMENT_REQUEST_EMPTY_EXCEPTION);
        return result;
    }
    public RecruitmentRequestResponse getRecruitmentRequestById(int id){
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RecruitmentRequestErrorMessage.RECRUITMENT_REQUEST_NOT_FOUND_EXCEPTION));
        RecruitmentRequestResponse response = modelMapper.map(recruitmentRequest, RecruitmentRequestResponse.class);

        if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
            response.setSalaryDetail(
                    (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
        } else if (recruitmentRequest.getSalaryFrom() == null
                && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
        } else if (recruitmentRequest.getSalaryTo() == null
                && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
        } else
            response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
        return response;
    }
    public ResponseWithTotalPage<RecruitmentRequestResponse> getAllRecruitmentRequestByCreator(int id, int pageNo, int pageSize){
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<RecruitmentRequest> pageResult = recruitmentRequestRepository.findByCreator(enterprise, pageable);
        List<RecruitmentRequestResponse> list = new ArrayList<RecruitmentRequestResponse>();
        ResponseWithTotalPage<RecruitmentRequestResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (RecruitmentRequest recruitmentRequest : pageResult.getContent()) {
                RecruitmentRequestResponse response = modelMapper.map(recruitmentRequest, RecruitmentRequestResponse.class);
                if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
                    response.setSalaryDetail(
                            (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
                } else if (recruitmentRequest.getSalaryFrom() == null
                        && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
                } else if (recruitmentRequest.getSalaryTo() == null
                        && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
                } else
                    response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
                list.add(response);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(RecruitmentRequestErrorMessage.LIST_RECRUITMENT_REQUEST_EMPTY_EXCEPTION);
        return result;
    }
    public ResponseWithTotalPage<RecruitmentRequestResponse> getAllClosedRecruitmentRequest(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<RecruitmentRequest> pageResult = recruitmentRequestRepository.findByStatus(RecruitmentRequestStatus.CLOSED, pageable);
        List<RecruitmentRequestResponse> list = new ArrayList<RecruitmentRequestResponse>();
        ResponseWithTotalPage<RecruitmentRequestResponse> result = new ResponseWithTotalPage<>();

        if (pageResult.hasContent()) {
            for (RecruitmentRequest recruitmentRequest : pageResult.getContent()) {
                RecruitmentRequestResponse response = modelMapper.map(recruitmentRequest,
                        RecruitmentRequestResponse.class);
                if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
                    response.setSalaryDetail(
                            (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - "
                                    + recruitmentRequest.getSalaryTo())
                                    .trim());
                } else if (recruitmentRequest.getSalaryFrom() == null
                        && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
                } else if (recruitmentRequest.getSalaryTo() == null
                        && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
                    response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
                } else
                    response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
                list.add(response);
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else
            throw new ListEmptyException(RecruitmentRequestErrorMessage.LIST_RECRUITMENT_REQUEST_EMPTY_EXCEPTION);
        return result;
    }
    public RecruitmentRequestResponse updateRecruitmentRequest(int id, RecruitmentRequestUpdateDTO updateDTO){
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.findById(id).orElseThrow(
                () -> new NotFoundException(RecruitmentRequestErrorMessage.RECRUITMENT_REQUEST_NOT_FOUND_EXCEPTION));

        if (updateDTO.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)
                && updateDTO.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            updateDTO.setSalaryTo(null);
        } else if (updateDTO.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
            updateDTO.setSalaryFrom(null);
        } else if (updateDTO.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            updateDTO.setSalaryTo(null);
        }
        if (recruitmentRequest != null){
            return mappedRecruitmentRequest(recruitmentRequest);
        }
        RecruitmentRequest recruitmentRequestSaved = recruitmentRequestRepository.save(recruitmentRequest);
        RecruitmentRequestResponse response = modelMapper.map(recruitmentRequestSaved, RecruitmentRequestResponse.class);
        if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
            response.setSalaryDetail(
                    (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
        } else if (recruitmentRequest.getSalaryFrom() == null
                && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
        } else if (recruitmentRequest.getSalaryTo() == null
                && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
        } else
            response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
        return response;
    }
    public RecruitmentRequestResponse createRecruitmentRequest(RecruitmentRequestCreateDTO createDTO){
        Optional<Enterprise> optionalCreator = enterpriseRepository.findById(createDTO.getEnterpriseId());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalDate dateFormat = LocalDate.parse(date.toString(), format);
        LocalDate expiryDate = LocalDate.parse(createDTO.getExpiryDate().toString(), format);

        if (optionalCreator.isPresent()) {
            RecruitmentRequest request = RecruitmentRequest.builder().date(Date.valueOf(dateFormat))
                    .expiryDate(Date.valueOf(expiryDate))
                    .industry(createDTO.getIndustry())
                    .amount(createDTO.getAmount())
                    .jobLevel(createDTO.getJobLevel())
                    .status(RecruitmentRequestStatus.OPENING)
                    .experience(createDTO.getExperience())
                    .typeOfWork(createDTO.getTypeOfWork())
                    .name(createDTO.getName())
                    .requirement(createDTO.getRequirement())
                    .salaryFrom(createDTO.getSalaryFrom())
                    .salaryTo(createDTO.getSalaryTo())
                    .description(createDTO.getDescription())
                    .creator(optionalCreator.get())
                    .build();

            RecruitmentRequest saveResult = recruitmentRequestRepository.save(request);

            // save data for Request_skill table
            if (Objects.nonNull(saveResult) && Objects.nonNull(saveResult.getId())) {
                List<RequestSkill> requestSkills = new ArrayList<>();
                createDTO.getSkillIds().forEach(
                        id -> requestSkills.add(new RequestSkill(saveResult.getId(), id)));
                requestSkillService.saveAll(requestSkills);
            }

            recruitmentRequestRepository.save(request);
            RecruitmentRequestResponse response = modelMapper.map(request, RecruitmentRequestResponse.class);
            if (request.getSalaryTo() != null && request.getSalaryFrom() != null) {
                response.setSalaryDetail(
                        (request.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + request.getSalaryTo())
                                .trim());
            } else if (request.getSalaryFrom() == null
                    && !request.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
                response.setSalaryDetail("Up to " + request.getSalaryTo());
            } else if (request.getSalaryTo() == null
                    && !request.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
                response.setSalaryDetail("More than " + request.getSalaryFrom());
            } else
                response.setSalaryDetail(request.getSalaryFrom());
            return response;
        } else {
            throw new NotFoundException(EnterpriseErrorMessage.ENTERPRISE_NOT_FOUND_EXCEPTION);
        }
    }
    public RecruitmentRequestResponse closeRecruitmentRequest(int id){
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RecruitmentRequestErrorMessage.RECRUITMENT_REQUEST_NOT_FOUND_EXCEPTION));
        recruitmentRequest.setStatus(RecruitmentRequestStatus.CLOSED);
        RecruitmentRequest recruitmentRequestSaved = recruitmentRequestRepository.save(recruitmentRequest);
        RecruitmentRequestResponse response = modelMapper.map(recruitmentRequestSaved, RecruitmentRequestResponse.class);

        if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
            response.setSalaryDetail(
                    (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
        } else if (recruitmentRequest.getSalaryFrom() == null
                && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
        } else if (recruitmentRequest.getSalaryTo() == null
                && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
        } else
            response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
        return response;
    }

    public RecruitmentRequestResponse openRecruitmentRequest(int id){
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RecruitmentRequestErrorMessage.RECRUITMENT_REQUEST_NOT_FOUND_EXCEPTION));
        recruitmentRequest.setStatus(RecruitmentRequestStatus.OPENING);
        RecruitmentRequest recruitmentRequestSaved = recruitmentRequestRepository.save(recruitmentRequest);
        RecruitmentRequestResponse response = modelMapper.map(recruitmentRequestSaved, RecruitmentRequestResponse.class);

        if (recruitmentRequest.getSalaryTo() != null && recruitmentRequest.getSalaryFrom() != null) {
            response.setSalaryDetail(
                    (recruitmentRequest.getSalaryFrom().replaceAll("VNĐ", "").trim() + " - " + recruitmentRequest.getSalaryTo()).trim());
        } else if (recruitmentRequest.getSalaryFrom() == null
                && !recruitmentRequest.getSalaryTo().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("Up to " + recruitmentRequest.getSalaryTo());
        } else if (recruitmentRequest.getSalaryTo() == null
                && !recruitmentRequest.getSalaryFrom().equalsIgnoreCase(THOA_THUAN)) {
            response.setSalaryDetail("More than " + recruitmentRequest.getSalaryFrom());
        } else
            response.setSalaryDetail(recruitmentRequest.getSalaryFrom());
        return response;
    }
    public RecruitmentRequestResponse mappedRecruitmentRequest(RecruitmentRequest recruitmentRequest){
        RecruitmentRequestResponse save = new RecruitmentRequestResponse();
        recruitmentRequest.setAmount(recruitmentRequest.getAmount());
        recruitmentRequest.setExpiryDate(recruitmentRequest.getExpiryDate());
        recruitmentRequest.setIndustry(recruitmentRequest.getIndustry());
        recruitmentRequest.setJobLevel(recruitmentRequest.getJobLevel());
        recruitmentRequest.setExperience(recruitmentRequest.getExperience());
        recruitmentRequest.setSalaryFrom(recruitmentRequest.getSalaryFrom());
        recruitmentRequest.setSalaryTo(recruitmentRequest.getSalaryTo());
        recruitmentRequest.setName(recruitmentRequest.getName());
        recruitmentRequest.setTypeOfWork(recruitmentRequest.getTypeOfWork());
        recruitmentRequest.setDescription(recruitmentRequest.getDescription());
        return save;
    }

}
