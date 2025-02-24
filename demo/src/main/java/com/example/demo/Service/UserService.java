package com.example.demo.Service;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Repository.UserRepository;
import com.example.demo.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }

    public UserDTO updateUserRole(UUID id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert String to Enum
        try {
            user.setRole(Role.valueOf(role.toUpperCase())); // Convert input String to enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }


    public void deleteUser(UUID id) {
        userRepository.deleteById(id);

    }
}