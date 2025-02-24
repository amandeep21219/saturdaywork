package com.example.demo.Repository;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByEmail(String email);
    }

