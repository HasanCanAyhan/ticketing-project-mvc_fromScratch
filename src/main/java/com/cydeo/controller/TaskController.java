package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @GetMapping("/show")
    public String getTaskCreatePage(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.readAll());
        model.addAttribute("employees", userService.getEmployees());
        model.addAttribute("tasks", taskService.readAll());

        return "/task/create";

    }

    @PostMapping("/insert")
    public String saveTask(@ModelAttribute("task") TaskDTO task){

        //there is no in the form assigned date and status and taskId

        taskService.save(task);


        return "redirect:/task/show";

    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId){

        taskService.deleteById(taskId);

        return "redirect:/task/show";
    }



}
