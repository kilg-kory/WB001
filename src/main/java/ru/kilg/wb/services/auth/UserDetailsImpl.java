package ru.kilg.wb.services.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<AuthGroup> authGroups;

    public UserDetailsImpl(User user, List<AuthGroup> authGroups) {
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroups == null) {
            return Collections.emptyList();
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(authGroup -> grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup())));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
