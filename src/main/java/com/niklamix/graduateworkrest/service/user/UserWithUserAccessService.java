package com.niklamix.graduateworkrest.service.user;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;

import javax.validation.Valid;

public interface UserWithUserAccessService {
    UserReadDTO getCurrentUser();
    void updateCurrentUser(@Valid UserWriteDTO userWriteDTO);
}
