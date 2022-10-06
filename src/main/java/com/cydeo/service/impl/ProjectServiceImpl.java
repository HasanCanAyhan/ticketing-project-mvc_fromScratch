package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapServiceDB<String,ProjectDTO> implements ProjectService {
    @Override
    public void save(ProjectDTO project) {

        if (project.getProjectStatus() == null){
            project.setProjectStatus(Status.OPEN);
        }

        super.save(project.getProjectCode(),project);
    }

    @Override
    public List<ProjectDTO> readAll() {
        return super.readAll();
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO project) { // this project comes from Controller side so UI-Part

        project.setProjectStatus(   findById(project.getProjectCode()).getProjectStatus()   );


        super.update(project.getProjectCode(),project);
    }


    @Override
    public void completeById(String projectCode) {
        findById(projectCode).setProjectStatus(Status.COMPLETE);
    }
}
