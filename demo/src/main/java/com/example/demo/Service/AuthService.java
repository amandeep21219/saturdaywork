package com.example.demo.Service;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.valueOf(userDTO.getRole()));


        userRepository.save(user);

    }

    public void logSuccessfulLoginAttempt(String username) {

    }

    public void logFailedLoginAttempt(String username) {

    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).get(0);

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