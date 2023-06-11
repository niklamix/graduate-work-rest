package com.niklamix.graduateworkrest.controller.user;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.filters.UserFilter;
import com.niklamix.graduateworkrest.service.admin.UserService;
import com.niklamix.graduateworkrest.service.user.UserWithUserAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/users")
public class UserWithUserAccessRESTController {
    private final UserWithUserAccessService userWithUserAccessService;
    private final UserService userService;
    @GetMapping
    public UserReadDTO getCurrentUser() {

        return userWithUserAccessService.getCurrentUser();
    }

    @GetMapping(value = "/filter")
    public List<UserReadDTO> showAllUsersByFilter(UserFilter userFilter) {

        return userService.getUsersByFilter(userFilter).toList();
    }

    @PutMapping
    public void updateCurrentUser(@RequestBody UserWriteDTO userWriteDTO) {

        userWithUserAccessService.updateCurrentUser(userWriteDTO);
    }
}
