package com.apits.apitssystembackend.service;

import com.apits.apitssystembackend.DTO.RoleCreateDTo;
import com.apits.apitssystembackend.DTO.RoleUpdateDTO;
import com.apits.apitssystembackend.entity.Role;
import com.apits.apitssystembackend.response.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public List<RoleResponse> getAllRoles();

    public Role getRoleById(int id);

    public Role getRoleByName(String name);
    public RoleResponse getRoleResponseById(int id);

    public RoleResponse createRole(RoleCreateDTo createDTo);

    public RoleResponse findByName(RoleCreateDTo createDTo);

    public RoleResponse updateRole(int roleID, RoleUpdateDTO updateDTO);
    public RoleResponse activateRole(int roleId);
    public RoleResponse removeRole(int id);


}
