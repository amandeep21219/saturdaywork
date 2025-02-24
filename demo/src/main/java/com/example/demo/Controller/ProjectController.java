package com.example.demo.Controller;
import com.example.demo.DTO.ProjectDTO;
import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable UUID id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable UUID id, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
    }
}