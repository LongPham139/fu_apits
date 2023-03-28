package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.PositionCreateDTO;
import com.apits.apitssystembackend.DTO.PositionUpdateDTO;
import com.apits.apitssystembackend.response.PositionResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import org.springframework.stereotype.Service;

@Service
public interface PositionService {
    public ResponseWithTotalPage<PositionResponse> getAllByPositions(int pageNo, int pageSize, String find);

    public PositionResponse getPositionById(int id);

    public PositionResponse createPosition(PositionCreateDTO createDTO);

    public PositionResponse updatePosition(int id, PositionUpdateDTO updateDTO);

    public void deletePosition(int id);

    void activePosition(int id);





}
