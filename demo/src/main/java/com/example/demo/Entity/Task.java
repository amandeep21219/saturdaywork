package com.example.demo.Entity;

import com.example.demo.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;//task id

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    @Column(nullable = false)
    private Status status; // Pending, In Progress, Completed, Blocked

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id", nullable = false)
    private User assignedUser;


}
