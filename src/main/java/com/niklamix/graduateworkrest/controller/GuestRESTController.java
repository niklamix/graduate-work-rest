package com.niklamix.graduateworkrest.controller;

import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.service.admin.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestRESTController {
    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserWriteDTO userWriteDTO) {

        userService.saveUser(userWriteDTO);
    }

}
