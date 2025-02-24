package com.example.demo.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private String status;
    private UUID projectId;
    private UUID assignedUserId;
}
