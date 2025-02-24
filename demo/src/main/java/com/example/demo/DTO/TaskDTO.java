package com.example.demo.DTO;

import com.example.demo.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskDTO {
    private String title;
    private Status status;
    private UUID projectId;
    private UUID assignedUserId;
}
