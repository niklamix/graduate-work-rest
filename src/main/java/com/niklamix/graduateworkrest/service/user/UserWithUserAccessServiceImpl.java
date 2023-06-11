package com.niklamix.graduateworkrest.service.user;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.entity.User;
import com.niklamix.graduateworkrest.mappers.UserMapper;
import com.niklamix.graduateworkrest.repository.UserRepository;
import com.niklamix.graduateworkrest.service.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserWithUserAccessServiceImpl implements UserWithUserAccessService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserReadDTO getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByLogin(userPrincipal.getUsername()).orElse(null);
        log.info(userPrincipal.getUsername() + " got information about the current user");
        return userMapper.toUserReadDTO(user);
    }

    @Override
    public void updateCurrentUser(@Valid UserWriteDTO userWriteDTO) {
        User updatedUser = userMapper.toUser(userWriteDTO);
        String password = passwordEncoder.encode(updatedUser.getPassword());
        if (!passwordEncoder.matches(getUserPrincipal().getPassword(), password)) {
            updatedUser.setPassword(password);
        }
        updatedUser.setId(getUserPrincipal().getId());
        updatedUser.setAdminFlag(getUserPrincipal().isAdmin());
        updatedUser.setEnabled(getUserPrincipal().isEnabled());
        log.info(getUserPrincipal().getUsername() + " got information about the current user");
        userRepository.save(updatedUser);


    }
    private UserPrincipal getUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
