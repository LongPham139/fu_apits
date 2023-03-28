package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.response.AssignCandidateResponse;
import com.apits.apitssystembackend.response.AssignResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;

public interface AssignService {
    public AssignResponse getAssignById(int id);
    public ResponseWithTotalPage<AssignResponse> getAllAssign(int pageNo, int pageSize);
    public ResponseWithTotalPage<AssignResponse> getAssignByEmployee(int employeeId, int pageNo, int pageSize);
    public ResponseWithTotalPage<AssignResponse> getAllAssignByRecruitmentRequest(int requestId, int pageNo, int pageSize);
    public ResponseWithTotalPage<AssignResponse> getAllPendingAssign(int pageNo, int pageSize);
    public ResponseWithTotalPage<AssignResponse> getAllApprovedAssign(int pageNo, int pageSize);
    public ResponseWithTotalPage<AssignResponse> getAllCancelAssign(int pageNo, int pageSize);
    public AssignResponse cancelAssign(int id, int employeeId);
    public AssignCandidateResponse approvedAssign(int id, int candidateId);
    public AssignCandidateResponse rejectedAssign(int id, int candidateId);
}
