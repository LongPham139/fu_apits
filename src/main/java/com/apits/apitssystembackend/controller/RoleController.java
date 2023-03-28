package com.apits.apitssystembackend.controller;

import com.apits.apitssystembackend.DTO.RoleCreateDTo;
import com.apits.apitssystembackend.DTO.RoleUpdateDTO;
import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.constant.role.RoleSuccessMessage;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import com.apits.apitssystembackend.response.RoleResponse;
import com.apits.apitssystembackend.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.apits.apitssystembackend.constant.role.RoleSuccessMessage.*;

@RestController
@RequestMapping("role")
@Tag(name = "Role Controller")
@RequiredArgsConstructor
public class RoleController {


    private final RoleService roleService;


    @GetMapping()
    public ResponseEntity<ListResponseDTO> getAllRoles() {
        ListResponseDTO<RoleResponse> response = new ListResponseDTO();
        List<RoleResponse> list = roleService.getAllRoles();
        response.setData(list);
        response.setMessage(RoleSuccessMessage.GET_ALL_ROLE);
        response.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getRoleById(@PathVariable(name = "id") int id) {
        ResponseDTO<RoleResponse> responseDTO = new ResponseDTO<>();
        RoleResponse role = roleService.getRoleResponseById(id);
        responseDTO.setData(role);
        responseDTO.setMessage(RoleSuccessMessage.GET_ALL_ROLE);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> creatRole(@RequestBody RoleCreateDTo createDTo) {
        ResponseDTO<RoleResponse> responseDTO = new ResponseDTO();
        RoleResponse role = roleService.createRole(createDTo);
        responseDTO.setData(role);
        responseDTO.setMessage(CREATE_ROLE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("findByName")
    public ResponseEntity<?> findByName(@RequestBody RoleCreateDTo createDTo) {
        if (createDTo.getName().isEmpty() || createDTo.getName().isBlank()) {
            return getAllRoles();
        } else {
            ResponseDTO<RoleResponse> responseDTO = new ResponseDTO();
            RoleResponse role = roleService.findByName(createDTo);
            responseDTO.setData(role);
            responseDTO.setMessage(FIND_ROLE_SUCCESS);
            responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @PutMapping("update/{roleID}")
    public ResponseEntity<ResponseDTO> updateRole(@PathVariable("roleID") int id, @RequestBody RoleUpdateDTO updateDTO) {
        ResponseDTO<RoleResponse> responseDTO = new ResponseDTO();
        RoleResponse role = roleService.updateRole(id, updateDTO);
        responseDTO.setData(role);
        responseDTO.setMessage(UPDATE_ROLE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping("remove/{id}")
    public ResponseEntity<ResponseDTO> removeRole(@PathVariable("id") int id){
        ResponseDTO<RoleResponse> responseDTO = new ResponseDTO<>();
        RoleResponse role = roleService.removeRole(id);
        responseDTO.setMessage(DISABLE_ROLE_SUCCESS);
        responseDTO.setData(role);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("activeRole/{id}")
    public ResponseEntity<ResponseDTO> activeRole(@PathVariable(name = "id") int roleID){
        ResponseDTO<RoleResponse> responseDTO = new ResponseDTO<>();
        RoleResponse role = roleService.activateRole(roleID);
        responseDTO.setData(role);
        responseDTO.setMessage(ACTIVE_ROLE_SUCCESS);
        responseDTO.setStatus(ResponseStatusDTO.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
