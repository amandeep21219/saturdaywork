package com.example.demo.Controller;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.UserDetailServiceImpl;
import com.example.demo.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        authService.registerUser(userDTO);
        return ResponseEntity.ok("Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            // Generate JWT token
            String jwtToken = JwtUtil.
                    generateToken(userDetails.getUsername());

            return ResponseEntity.ok(jwtToken);
        } catch (Exception e) {
           return null;
        }


    }
}


