package com.niklamix.graduateworkrest.controller.admin;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.filters.PageFilter;
import com.niklamix.graduateworkrest.filters.UserFilter;
import com.niklamix.graduateworkrest.service.admin.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class UserRESTController {
    private final UserService userService;

    @GetMapping
    public List<UserReadDTO> getAllUsers(PageFilter pageFilter) {

        return userService.getAllUsers(pageFilter).toList();
    }
    @GetMapping(value = "/filter")
    public List<UserReadDTO> getAllUsersByFilter(UserFilter userFilter) {

        return userService.getUsersByFilter(userFilter).toList();
    }

    @GetMapping("/{id}")
    public UserReadDTO getUser(@PathVariable long id) {

        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserWriteDTO userWriteDTO) {

        userService.saveUser(userWriteDTO);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UserWriteDTO userWriteDTO) {

        userService.updateUserById(id, userWriteDTO);
    }
    @GetMapping("/{id}/admin")
    public void changeAdminFlag(@PathVariable long id) {
        userService.changeAdminFlag(id);
    }
    @GetMapping("/{id}/enabled")
    public void changeEnabledFlag(@PathVariable long id) {
        userService.changeEnabledFlag(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {

        userService.deleteUserById(id);
    }
}
