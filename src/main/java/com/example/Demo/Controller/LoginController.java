package com.example.Demo.Controller;


import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
@Autowired
private UserService userService;

    @GetMapping("/api/Userlogin")
    public ResponseEntity login(Principal principal){

        Map<String,Object> map = new HashMap<>();
        map.put("userId", userService.getByEmail(principal.getName()).getUserid());
        map.put("role", userService.getByEmail(principal.getName()).getRole().getUsertype());
        return ResponseEntity.ok(map);
    }
}
