package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.RoleDTO;
import com.example.Demo.Model.Role;
import com.example.Demo.Repository.RoleRepository;
import com.example.Demo.Service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleImp implements RoleService {

    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public RoleDTO addRole(RoleDTO roleDTO){
        Role role = modelMapper.map(roleDTO,Role.class);
        roleRepository.save(role);
        return roleDTO;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roleEntitys = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for(Role role : roleEntitys){
            RoleDTO roleDTO = modelMapper.map(role,RoleDTO.class);
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

    @Override
    public List<RoleDTO> getAllRolesAvailable() {
        List<Role> roleEntitys = roleRepository.findAllByAvailabilityIsTrue();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for(Role role : roleEntitys){
            RoleDTO roleDTO = modelMapper.map(role,RoleDTO.class);
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

    @Override
    public RoleDTO getRole(Long id){
        RoleDTO roleDTO = modelMapper.map(roleRepository.findById(id).get(),RoleDTO.class);
        return roleDTO;
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO, Long id) {
        Role role = roleRepository.findById(id).get();
        Role role1 = modelMapper.map(roleDTO,Role.class);
        role1.setAvailability(role.isAvailability());
        roleRepository.save(role1);
        return roleDTO;
    }

    @Override
    public void deleteRole(Long id){
        Role role = roleRepository.findById(id).get();
        role.setAvailability(false);
        roleRepository.save(role);
    }

    @Override
    public void deleteAll(){
        roleRepository.deleteAll();
        roleRepository.flush();
    }

}
