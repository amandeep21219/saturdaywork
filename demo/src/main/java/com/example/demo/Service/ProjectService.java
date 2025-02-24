package com.example.demo.Service;
import com.example.demo.DTO.ProjectDTO;
import com.example.demo.Entity.Project;
import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDTO> getProjectById(UUID id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        
        return projectMapper.toDTO(project);
    }

    public ProjectDTO updateProject(UUID id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(projectDTO.getName());
        project = projectRepository.save(project);
       
        return projectMapper.toDTO(project);
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
       
    }
}