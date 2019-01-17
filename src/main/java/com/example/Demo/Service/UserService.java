package com.example.Demo.Service;

import com.example.Demo.DTO.UserDTO;
import com.example.Demo.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUsersAvailable();
    User addUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    UserDTO updateUser(UserDTO userDTO,Long id);
    void deleteUser(Long id);
    void deleteAll();
    UserDTO getByEmail(String email);

}
