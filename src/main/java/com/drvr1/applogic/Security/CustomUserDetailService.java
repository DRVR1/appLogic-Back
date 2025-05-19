package com.drvr1.applogic.Security;

import com.drvr1.applogic.Services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userEntityService.findByEmail(username).orElseThrow();
        return new UserPrincipal(user.getId(), user.getEmail(),user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
