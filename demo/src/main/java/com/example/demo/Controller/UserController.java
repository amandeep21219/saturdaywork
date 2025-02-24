package com.example.demo.Controller;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {//ok
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public UserDTO createUser(@RequestBody UserDTO userDTO) {
//        return userService.createUser(userDTO);
//    }

    @PatchMapping("/{id}/role")
    public UserDTO updateUserRole(@PathVariable UUID id, @RequestParam String role) {//ok
        return userService.updateUserRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }//ok
}