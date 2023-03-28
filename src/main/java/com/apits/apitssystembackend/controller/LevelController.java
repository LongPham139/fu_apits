package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.LevelCreateDTO;
import com.apits.apitssystembackend.constant.level.LevelSuccessMessage;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.response.LevelResponse;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.service.LevelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("level")
@RequiredArgsConstructor
@Tag(name = "Level Controller")
public class LevelController {
    private final LevelService levelService;
    @GetMapping("getAllPaging")
    public ResponseEntity<ListResponseDTO> getAllLevelPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ListResponseDTO<LevelResponse> responseDTO = new ListResponseDTO<>();
        List<LevelResponse> list = levelService.getAllLevelPaging(pageNo, pageSize);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setData(list);
        responseDTO.setMessage(LevelSuccessMessage.GET_ALL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("getLevelById/{id}")
    public ResponseEntity<ResponseDTO> getLevelById(@PathVariable(name = "id") int id){
        ResponseDTO<LevelResponse> responseDTO = new ResponseDTO<>();
        LevelResponse level = levelService.getLevelById(id);
        responseDTO.setData(level);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        responseDTO.setMessage(LevelSuccessMessage.GET_LEVEL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
//    @PostMapping("getLevelBySpecialId/{specialId}")
//    public ResponseEntity<ListResponseDTO> getLevelBySpecialId(@PathVariable(name = "specialId") int specialId){
//        ListResponseDTO<LevelResponse> responseDTO = new ListResponseDTO<>();
//        List<LevelResponse> list = levelService.getLevelBySpecialId(specialId);
//        responseDTO.setMessage(LevelSuccessMessage.GET_LEVEL_BY_SPECIAL_SUCCESS);
//        responseDTO.setData(list);
//        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
//        return ResponseEntity.ok().body(responseDTO);
//    }
    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createLevel(@RequestBody LevelCreateDTO dto){
        ResponseDTO<LevelResponse> responseDTO = new ResponseDTO<>();
        LevelResponse levelResponse = levelService.createLevel(dto);
        responseDTO.setData(levelResponse);
        responseDTO.setMessage(LevelSuccessMessage.CREATE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("disable/{id}")
    public ResponseEntity<ResponseDTO> disableLevel(@PathVariable(name = "id") int id){
        ResponseDTO<LevelResponse> responseDTO = new ResponseDTO<>();
        LevelResponse levelResponse = levelService.disableLevel(id);
        responseDTO.setData(levelResponse);
        responseDTO.setMessage(LevelSuccessMessage.DISABLE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("enable/{id}")
    public ResponseEntity<ResponseDTO> enableLevel(@PathVariable(name = "id") int id){
        ResponseDTO<LevelResponse> responseDTO = new ResponseDTO<>();
        LevelResponse levelResponse = levelService.enableLevel(id);
        responseDTO.setData(levelResponse);
        responseDTO.setMessage(LevelSuccessMessage.ENABLE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
