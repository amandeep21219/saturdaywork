package com.example.demo.DTO;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class UserDTO {

        private String email;
        private String password;
        private Role role;
        private String firstname;
        private String lastname;
}
