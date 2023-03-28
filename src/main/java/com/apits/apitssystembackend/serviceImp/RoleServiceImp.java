package com.apits.apitssystembackend.serviceImp;

import com.apits.apitssystembackend.DTO.RoleCreateDTo;
import com.apits.apitssystembackend.DTO.RoleUpdateDTO;
import com.apits.apitssystembackend.constant.role.RoleFailMessage;
import com.apits.apitssystembackend.constant.role.RoleStatus;
import com.apits.apitssystembackend.constant.role.RoleSuccessMessage;
import com.apits.apitssystembackend.entity.Role;
import com.apits.apitssystembackend.exceptions.ExistException;
import com.apits.apitssystembackend.exceptions.ListEmptyException;
import com.apits.apitssystembackend.exceptions.NotFoundException;
import com.apits.apitssystembackend.repository.RoleRepository;
import com.apits.apitssystembackend.response.RoleResponse;
import com.apits.apitssystembackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> listRoleR = new ArrayList<>();
        List<Role> list = roleRepository.findAll();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                RoleResponse tmp = mappedRole(list.get(i));
                listRoleR.add(tmp);
            }
        } else
            throw new ListEmptyException(RoleFailMessage.LIST_ROLE_EMPTY);
        return listRoleR;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = roleRepository.findById(id);
        if (role == null) {
            throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
        } else {
            return role;
        }

    }

    @Override
    public Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
        } else {
            return role;
        }
    }


    @Override
    public RoleResponse createRole(RoleCreateDTo createDTo) {
        Role tmp = roleRepository.findByName(createDTo.getName());
        if (tmp == null) {
            Role role = Role.builder()
                    .name(createDTo.getName())
                    .status(RoleStatus.ACTIVATED)
                    .build();
            roleRepository.save(role);
            return mappedRole(role);
        } else {
            throw new ExistException(RoleFailMessage.ROLE_EXIST_EXCEPTION);
        }

    }

    @Override
    public RoleResponse findByName(RoleCreateDTo createDTo) {
        Role role = roleRepository.findByName(createDTo.getName());
        if (role != null) {
            return mappedRole(role);
        } else throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
    }

    @Override
    public RoleResponse updateRole(int id, RoleUpdateDTO updateDTO) {
        Role role = getRoleById(id);
        if (role != null) {
            role.setName(updateDTO.getName());
            roleRepository.save(role);
            return mappedRole(role);
        } else throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
    }

    @Override
    public RoleResponse activateRole(int roleId) {
        Role role = getRoleById(roleId);
        if(role != null){
            role.setStatus(RoleStatus.ACTIVATED);
            roleRepository.save(role);
        }else throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
        return mappedRole(role);
    }

    @Override
    public RoleResponse removeRole(int id) {
        Role role = getRoleById(id);
        if(role != null){
            role.setStatus(RoleStatus.DISABLED);
            roleRepository.save(role);
            return mappedRole(role);
        }else throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
    }

    @Override
    public RoleResponse getRoleResponseById(int id) {
        Role role = getRoleById(id);
        if (role != null){
            return mappedRole(role);
        }else
            throw new NotFoundException(RoleFailMessage.ROLE_NOT_EXIST);
    }

    public RoleResponse mappedRole(Role role) {
        RoleResponse tmp = new RoleResponse();
        tmp.setRoleID(role.getId());
        tmp.setRoleStatus(role.getStatus());
        tmp.setRoleName(role.getName());
        return tmp;
    }
}
