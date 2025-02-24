package com.example.demo.Mapper;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.Entity.Task;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
//public class TaskMapper {

//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Task toEntity(TaskDTO taskDTO) {
//        Task task = modelMapper.map(taskDTO, Task.class);
//        task.setProject(projectRepository.findById(taskDTO.getProjectId()).orElseThrow());
//        task.setAssignedUser(userRepository.findById(taskDTO.getAssignedUserId()).orElseThrow());
//        return task;
//    }
//
//    public TaskDTO toDTO(Task task) {
//        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
//        taskDTO.setProjectId(task.getProject().getId());
//        taskDTO.setAssignedUserId(task.getAssignedUser().getId());
//        return taskDTO;
//    }
//}

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

//    public Task toEntity(TaskDTO taskDTO) {
//        Task task = modelMapper.map(taskDTO, Task.class);
//        task.setProject(projectRepository.findById(taskDTO.getProjectId()).orElseThrow());
//        task.setAssignedUser(userRepository.findById(taskDTO.getAssignedUserId()).orElseThrow());
//        return task;
 //   }
public Task toEntity(TaskDTO taskDTO) {
    Task task = modelMapper.map(taskDTO, Task.class);

    if (taskDTO.getProjectId() != null) {
        task.setProject(projectRepository.findById(taskDTO.getProjectId()).orElse(null));
    }

    if (taskDTO.getAssignedUserId() != null) {
        task.setAssignedUser(userRepository.findById(taskDTO.getAssignedUserId()).orElse(null));
    }

    return task;
}






    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setAssignedUserId(task.getAssignedUser().getId());
        return taskDTO;
    }
}
