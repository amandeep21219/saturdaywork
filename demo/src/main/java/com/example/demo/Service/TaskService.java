package com.example.demo.Service;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.Entity.Task;
import com.example.demo.Mapper.TaskMapper;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

   

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(UUID id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDTO);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        task = taskRepository.save(task);

        return taskMapper.toDTO(task);
    }

    public TaskDTO updateTaskStatus(UUID id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Convert String to Enum
        try {
            task.setStatus(Status.valueOf(status.toUpperCase())); // Convert input String to enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        task = taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);

    }
}