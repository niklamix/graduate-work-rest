package com.niklamix.graduateworkrest.service.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Boolean isAdminFlag;
    private final Boolean isEnabled;

    public UserPrincipal(Long id, String username, String password, Boolean isAdminFlag, Boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdminFlag = isAdminFlag;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        roleList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (Boolean.TRUE.equals(isAdminFlag)) {
            roleList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAdmin() {
        return isAdminFlag;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
