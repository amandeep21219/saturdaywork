package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;
import java.util.UUID;
@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//id of project
    private UUID id;

    @Column(nullable = false)
    private String name;//project name

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;
}

