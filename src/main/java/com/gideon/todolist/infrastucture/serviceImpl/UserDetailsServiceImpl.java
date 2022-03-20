package com.gideon.todolist.infrastucture.serviceImpl;

import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.infrastucture.persistence.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.util.Collection;
import java.util.Collections;

@Service
@Named
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("No User Found " + username));

        return new User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities("ROLE_USER"));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user){
        return Collections.singleton(new SimpleGrantedAuthority(role_user));
    }
}
