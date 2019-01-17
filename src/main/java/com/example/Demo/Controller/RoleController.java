package com.example.Demo.Controller;


import com.example.Demo.DTO.RoleDTO;
import com.example.Demo.Model.Role;
import com.example.Demo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public RoleService roleService;

    @GetMapping("/findall")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/findallavailable")
    public ResponseEntity<List<RoleDTO>> getAllRolesAvailable(){
        return ResponseEntity.ok(roleService.getAllRolesAvailable());
    }

    @PostMapping(value="/add")
     public ResponseEntity addRole(@RequestBody @Valid RoleDTO roleDTO, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
       return ResponseEntity.ok(roleService.addRole(roleDTO));
    }

    @GetMapping(value="/getbyid/{id}")
    public ResponseEntity getRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateRole(@RequestBody @Valid RoleDTO roleDTO,@PathVariable Long id){
        return ResponseEntity.ok(roleService.updateRole(roleDTO,id));

    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    @DeleteMapping(value="/delete")
    public void deleteAllRole(){
        roleService.deleteAll();
    }

}
