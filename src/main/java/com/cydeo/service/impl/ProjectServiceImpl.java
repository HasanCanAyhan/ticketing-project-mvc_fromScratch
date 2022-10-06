package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapServiceDB<String,ProjectDTO> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

    @Override
    public List<ProjectDTO> getProjectsForSpecificManager(UserDTO manager) {

        // we have set Unfinished-Completed fields

        List<ProjectDTO> projectList =

                // we found first projects which belong to the specific manager
                readAll().stream().filter(project -> project.getAssignedManager().equals(manager))
                        .map(project ->  {
                            // all tasks belongs to the project

                            int unfinishedTasksCount = (int) taskService.readAll().stream().filter(task -> task.getProject().getAssignedManager().equals(manager) )
                                    .filter(task->task.getProject().equals(project) )
                                    .filter(task -> task.getTaskStatus() != Status.COMPLETE).count();

                            int completedTasksCount = (int) taskService.readAll().stream().filter(task -> task.getProject().getAssignedManager().equals(manager))
                                            .filter(task -> task.getProject().equals(project))
                                                    .filter(task -> task.getTaskStatus() == Status.COMPLETE).count();

                            project.setUnfinishedTasksCount(unfinishedTasksCount);
                            project.setCompletedTasksCount(completedTasksCount);

                            return project;

                        } ).collect(Collectors.toList());







        return projectList;
    }
}
