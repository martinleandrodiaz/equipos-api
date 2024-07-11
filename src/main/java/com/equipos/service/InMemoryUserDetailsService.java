package com.equipos.service;

import com.equipos.exception.ServiceException;
import com.equipos.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    public static final String USER = "test";
    public static final String USER_PASSWORD = "12345";
    public static final String USER_ROLE = "USER";
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public InMemoryUserDetailsService() {
        users.put(USER,new User(USER, "{noop}"+USER_PASSWORD, List.of(USER_ROLE)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(users.get(username))
                .map(this::getUser)
                .orElseThrow(() -> new ServiceException("Credenciales inválidas", HttpStatus.UNAUTHORIZED));
    }

    private UserDetails getUser(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.username())
                .password(user.password())
                .roles(user.roles().toArray(new String[0]))
                .build();
    }
}
