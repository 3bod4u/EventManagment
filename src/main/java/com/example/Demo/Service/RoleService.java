package com.example.Demo.Service;

import com.example.Demo.DTO.RoleDTO;
import com.example.Demo.Model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;

@Service
public interface RoleService {

    List<RoleDTO> getAllRoles();
    List<RoleDTO> getAllRolesAvailable();
    RoleDTO addRole(RoleDTO roleDTO);
    RoleDTO getRole(Long id);
    RoleDTO updateRole(RoleDTO roleDTO,Long id);
    void deleteRole(Long id);
    void deleteAll();

}
