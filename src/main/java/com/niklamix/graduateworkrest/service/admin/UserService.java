package com.niklamix.graduateworkrest.service.admin;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.filters.PageFilter;
import com.niklamix.graduateworkrest.filters.UserFilter;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {
    Page<UserReadDTO> getAllUsers(PageFilter pageFilter);
    Page<UserReadDTO> getUsersByFilter(UserFilter userFilter);
    UserReadDTO getUserById(@NotNull long id);
    void saveUser(@Valid UserWriteDTO userWriteDTO);
    void changeAdminFlag(@NotNull long id);
    void changeEnabledFlag(@NotNull long id);
    void updateUserById(@NotNull long id, @Valid UserWriteDTO userWriteDTO);
    void deleteUserById(@NotNull long id);
}
