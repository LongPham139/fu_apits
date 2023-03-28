package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.PositionCreateDTO;
import com.apits.apitssystembackend.DTO.PositionUpdateDTO;
import com.apits.apitssystembackend.constant.employee.EmployeeStatus;
import com.apits.apitssystembackend.constant.position.PositionErrorMessage;
import com.apits.apitssystembackend.constant.position.PositionStatus;
import com.apits.apitssystembackend.entity.Employee;
import com.apits.apitssystembackend.entity.Position;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.PositionRepository;
import com.apits.apitssystembackend.response.PositionResponse;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImp implements PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    @Override
    public PositionResponse getPositionById(int id){
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PositionErrorMessage.POSITION_DISABLE_ERROR));
        PositionResponse response = mapper.map(position, PositionResponse.class);
        return response;
    }

    public PositionResponse createPosition(PositionCreateDTO createDTO) {
        Position position = Position.builder()
                .name(createDTO.getName())
                .status(PositionStatus.ACTIVITE)
                .build();
        positionRepository.save(position);
        PositionResponse response = mapper.map(position, PositionResponse.class);
        return response;
    }


    public PositionResponse updatePosition(int id, PositionUpdateDTO updateDTO) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PositionErrorMessage.POSITION_NOT_EXIST));
        position.setName(updateDTO.getName());
        positionRepository.save(position);
        PositionResponse response = mapper.map(position, PositionResponse.class);
        return response;
    }


    public void deletePosition(int id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PositionErrorMessage.POSITION_NOT_EXIST));
        if (position.getEmployees().size() > 0 ){
            throw new ExistException(PositionErrorMessage.POSITION_DISABLE_ERROR);
        } else {
            position.setStatus(PositionStatus.DISABLE);
            positionRepository.save(position);
        }
    }


    public void activePosition(int id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PositionErrorMessage.POSITION_NOT_EXIST));
        position.setStatus(PositionStatus.ACTIVITE);
        positionRepository.save(position);
    }


    public ResponseWithTotalPage<PositionResponse> getAllByPositions(int pageNo, int pageSize, String find) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Position> pageResult = positionRepository.findAll(pageable);
        List<PositionResponse> list = new ArrayList<>();
        ResponseWithTotalPage<PositionResponse> result = new ResponseWithTotalPage<>();
        if (pageResult.hasContent()) {
            for (Position position : pageResult.getContent()) {
                if (position.getName().toLowerCase().contains(find.toLowerCase())) {
                    int total = positionRepository.countUsedPosition(position.getId());
                    PositionResponse response = mapper.map(position, PositionResponse.class);
                    response.setNumberUsePosition(total);
                    list.add(response);
                }
            }
            result.setResponseList(list);
            result.setTotalPage(pageResult.getTotalPages());
        } else {
            throw new ListEmptyException(PositionErrorMessage.LIST_POSITION_EMPTY);
        }
        return result;
    }

}
