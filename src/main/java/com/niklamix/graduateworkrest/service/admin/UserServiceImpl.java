package com.niklamix.graduateworkrest.service.admin;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.entity.User;
import com.niklamix.graduateworkrest.exception.handing.NoSuchEntityException;
import com.niklamix.graduateworkrest.filters.PageFilter;
import com.niklamix.graduateworkrest.filters.UserFilter;
import com.niklamix.graduateworkrest.mappers.UserMapper;
import com.niklamix.graduateworkrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserServiceImpl implements UserService {
    private static final String NO_SUCH_USER_EXCEPTION = "There is no user with id = ";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserReadDTO> getAllUsers(PageFilter pageFilter) {
        Page<User> userPage = userRepository.findAll(pageFilter.getPageable());
        if (userPage.isEmpty()) {
            log.warn(getCurrentUsername() + "tired to get the list of users, but there are no users in the database");
            throw new NoSuchEntityException("There are no users in DataBase");
        }
        log.info(getCurrentUsername() + " got the list of all users");
        return userPage.map(userMapper::toUserReadDTO);
    }

    @Override
    public Page<UserReadDTO> getUsersByFilter(UserFilter userFilter) {
        Page<User> userPage = userRepository.findAll(
                getUserExample(userMapper.toUserWithFilter(userFilter)), userFilter.getPageable());
        if (userPage.isEmpty()) {
            log.warn(getCurrentUsername() + " tired to get the list of users with filter " +
                    userFilter + ", but there are no users in the database");
            throw new NoSuchEntityException("There are no users in DataBase");
        }
        log.info(getCurrentUsername() + " got the list of all users with filter " + userFilter);
        return userPage.map(userMapper::toUserReadDTO);
    }

    @Override
    public UserReadDTO getUserById(@NotNull long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn(getCurrentUsername() + " tired to get the non-existent user with id = " + id);
            throw new NoSuchEntityException(NO_SUCH_USER_EXCEPTION + id);
        }
        log.info(getCurrentUsername() + " received the user with id = " + id);
        return userMapper.toUserReadDTO(user);
    }

    @Override
    public void saveUser(@Valid UserWriteDTO userWriteDTO) {
        User user = userMapper.toUser(userWriteDTO);
        user.setAdminFlag(false);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info(getCurrentUsername() + " created a new user");
        userRepository.save(user);
    }

    @Override
    public void updateUserById(@NotNull long id, @Valid UserWriteDTO userWriteDTO) {
        User oldUser = userRepository.findById(id).orElse(null);
        User updatedUser = userMapper.toUser(userWriteDTO);
        String password = passwordEncoder.encode(updatedUser.getPassword());
        if (oldUser == null) {
            log.warn(getCurrentUsername() + " tired to update the non-existent user with id = " + id);
            throw new NoSuchEntityException(NO_SUCH_USER_EXCEPTION + id);
        }
        if (!passwordEncoder.matches(oldUser.getPassword(), password)) {

            updatedUser.setPassword(password);
        }
        updatedUser.setId(id);
        updatedUser.setAdminFlag(oldUser.isAdminFlag());
        updatedUser.setEnabled(oldUser.isEnabled());
        log.info(getCurrentUsername() + " updated the user with id = " + id);
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUserById(@NotNull long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn(getCurrentUsername() + " tired to delete the non-existent user with id = " + id);
            throw new NoSuchEntityException(NO_SUCH_USER_EXCEPTION + id);
        }
        log.info(getCurrentUsername() + " deleted the user with id = " + id);
        userRepository.deleteById(id);
    }
    public void changeAdminFlag(@NotNull long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn(getCurrentUsername() + " tired to delete the non-existent user with id = " + id);
            throw new NoSuchEntityException(NO_SUCH_USER_EXCEPTION + id);
        }
        user.setAdminFlag(!user.isAdminFlag());
        log.info(getCurrentUsername() + " change admin flag at the user with id =" + id
        + " now be: " + user.isAdminFlag());
        userRepository.save(user);
    }

    public void changeEnabledFlag(@NotNull long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn(getCurrentUsername() + " tired to delete the non-existent user with id = " + id);
            throw new NoSuchEntityException(NO_SUCH_USER_EXCEPTION + id);
        }
        user.setEnabled(!user.isEnabled());
        log.info(getCurrentUsername() + " change enabled flag at the user with id =" + id
                + " now be: " + user.isEnabled());
        userRepository.save(user);
    }

    private Example<User> getUserExample(User user) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("adminFlag")
                .withIgnorePaths("enabled")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return Example.of(user, matcher);
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
