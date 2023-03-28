package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.PositionCreateDTO;
import com.apits.apitssystembackend.DTO.PositionUpdateDTO;
import com.apits.apitssystembackend.constant.position.PositionSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.PositionResponse;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.ResponseWithTotalPage;
import com.apits.apitssystembackend.service.PositionService;
import com.apits.apitssystembackend.serviceImp.PositionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
public class PositionController {

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }
    private PositionService positionService;

    @GetMapping("/getAllPosition")
    public ResponseDTO getAllPositions(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "") String name) {

        ResponseDTO<ResponseWithTotalPage> response = new ResponseDTO();
        ResponseWithTotalPage<PositionResponse> list = positionService.getAllByPositions(pageNo, pageSize, name);
        response.setData(list);
        response.setMessage(PositionSuccessMessage.GET_ALL_POSITION_SUCCESS);
        response.setStatus(ResponseStatusDTO.SUCCESS);
        return response;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> getPositionById(@RequestParam("id") int id) {
        ResponseDTO<PositionResponse> responseDTO = new ResponseDTO();
        PositionResponse positionResponse = positionService.getPositionById(id);
        responseDTO.setData(positionResponse);
        responseDTO.setMessage(PositionSuccessMessage.GET_POSITION_BY_ID);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/create")
    public ResponseDTO createPosition(@RequestBody PositionCreateDTO createDTO) {
        ResponseDTO<PositionResponse> responseDTO = new ResponseDTO();
        PositionResponse positionResponse = positionService.createPosition(createDTO);
        responseDTO.setData(positionResponse);
        responseDTO.setMessage(PositionSuccessMessage.CREATE_POSITION);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return responseDTO;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updatePosition(@RequestParam("id") int id,
                                                    @RequestBody PositionUpdateDTO updateDTO) {
        ResponseDTO<PositionResponse> responseDTO = new ResponseDTO();
        PositionResponse positionResponse = positionService.updatePosition(id, updateDTO);
        responseDTO.setData(positionResponse);
        responseDTO.setMessage(PositionSuccessMessage.UPDATE_POSITION);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletePosition(@RequestParam("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO();
        positionService.deletePosition(id);
        responseDTO.setMessage(PositionSuccessMessage.DELETE_POSITION);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDTO> activePosition(@RequestParam("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO();
        positionService.activePosition(id);
        responseDTO.setMessage(PositionSuccessMessage.ACTIVE_POSITION);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }


}
