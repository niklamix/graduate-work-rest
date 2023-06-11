package com.niklamix.graduateworkrest.auditing;

import com.niklamix.graduateworkrest.service.principal.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(UserPrincipal.class::cast)
                .map(UserPrincipal::getUsername)
                .orElse("admin").describeConstable();
    }
}
