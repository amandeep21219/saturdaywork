package com.example.demo.Controller;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();//ok
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id) {//ok
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)//ok
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);//ok
    }

    @PatchMapping("/{id}")//ok
    public TaskDTO updateTaskStatus(@PathVariable UUID id, @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {//ok
        taskService.deleteTask(id);
    }
}