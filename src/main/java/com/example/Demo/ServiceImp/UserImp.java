package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.UserDTO;
import com.example.Demo.Model.User;
import com.example.Demo.Repository.RoleRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserImp implements UserService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public User addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userEntity = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : userEntity){
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @Override
    public List<UserDTO> getAllUsersAvailable() {
        List<User> userEntity = userRepository.findAllByAvailabilityIsTrue();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : userEntity){
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @Override
    public UserDTO getUser(Long id){
        UserDTO userDTO = modelMapper.map(userRepository.findById(id).get(),UserDTO.class);
        userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,Long id) {
        User user = userRepository.findById(id).get();
        User userEntity = modelMapper.map(userDTO,User.class);
        userEntity.setUserid(user.getUserid());
        userEntity.setAvailability(user.isAvailability());
        userEntity.setRole(user.getRole());
        userRepository.save(userEntity);
        UserDTO userdto = modelMapper.map(userEntity,UserDTO.class);
        return userdto;
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setAvailability(false);
        userRepository.save(user);
    }


    @Override
    public void deleteAll(){
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Override
    public UserDTO getByEmail(String email) {

        User user = userRepository.findByEmailAndAvailabilityIsTrue(email);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;

    }
}
