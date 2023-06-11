package com.niklamix.graduateworkrest.service.principal;

import com.niklamix.graduateworkrest.entity.User;
import com.niklamix.graduateworkrest.exception.handing.NoSuchEntityException;
import com.niklamix.graduateworkrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username).orElse(null);
        if (user == null) {
            throw new NoSuchEntityException("There is no user with login: " + username);
        }
        return new UserPrincipal(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.isAdminFlag(),
                user.isEnabled()
        );
    }
}
