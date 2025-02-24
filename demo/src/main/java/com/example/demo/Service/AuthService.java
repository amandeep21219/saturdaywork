package com.example.demo.Service;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);



    }
//    CustomerDao customerDao = Mapper.toDao(customerDto);
////         passwordEncoder.encode(customerDto.getPassword());
//        customerDao.setPassword(passwordEncoder.encode(customerDto.getPassword()));
//            customerRepository.save(customerDao);
//    // log.info("Customer Created");Created
//            return Mapper.toDto(customerDao);





//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email).get(0);
//
//        if (Objects.nonNull(user)) {
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getEmail())
//                    .password(user.getPassword())
//                    .roles(user.getRole().name())
//                    .build();
//        }
//        throw new UsernameNotFoundException("User not found with username: " + email);
//    }
}