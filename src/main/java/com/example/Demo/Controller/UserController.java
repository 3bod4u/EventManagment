package com.example.Demo.Controller;
import com.example.Demo.DTO.UserDTO;
import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid UserDTO userDTO, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @GetMapping
    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/findallavailable")
  //  @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<List<UserDTO>> findAllAvailable(){
        return ResponseEntity.ok(userService.getAllUsersAvailable());
    }

    @GetMapping(value="/{id}")
    //  @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping(value = "/{id}")
   // @PreAuthorize("(hasAnyRole('ADMIN','ORGNIZER','ATTENDER'))")
    public ResponseEntity updateUser(@RequestBody @Valid UserDTO userDTO,@PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(userDTO,id));

    }

    @DeleteMapping(value="/{id}")
   // @PreAuthorize("(hasAnyRole('ADMIN','ORGNIZER','ATTENDER'))")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @DeleteMapping(value="/delete")
  //  @PreAuthorize("(hasRole('ADMIN'))")
    public void deleteAllUsers(){
        userService.deleteAll();
    }

}
