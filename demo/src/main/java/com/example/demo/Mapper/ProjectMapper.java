package com.example.demo.Mapper;
import com.example.demo.DTO.ProjectDTO;
import com.example.demo.Entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


    @Component
    public class ProjectMapper {

        @Autowired
        private ModelMapper modelMapper;

        public Project toEntity(ProjectDTO projectDTO) {
            return modelMapper.map(projectDTO, Project.class);
        }

        public ProjectDTO toDTO(Project project) {
            return modelMapper.map(project, ProjectDTO.class);
        }
    }