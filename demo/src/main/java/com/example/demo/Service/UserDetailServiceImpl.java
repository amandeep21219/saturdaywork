package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.example.demo.Entity.User user = userRepository.findByEmail(email).get(0);

        if (Objects.nonNull(user)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + email);
    }

}