package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
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


    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.readAll());
        model.addAttribute("employees", userService.getEmployees());
        model.addAttribute("tasks", taskService.readAll());

        return "task/update";
    }

    @PostMapping("/update/{id}") // {id} : task id is not in form !! userName and projectCode are all in the form
    public String updateTask(@PathVariable("id") Long id,@ModelAttribute("task") TaskDTO task){

        // before update, assigned date and status should be not changed , bcs those are not in form

        task.setId(id);

        taskService.update(task);


        return "redirect:/task/show";
    }


    //Pending Task Page : not completed tasks

    @GetMapping("/pending")
    public String getPendingTasksPage(Model model){

        //project code and name, task subject, assignedEmployee,assigned date,status

        model.addAttribute("tasks", taskService.getTasksNotCompleted());





        return "/task/pending-tasks";

    }


    //Pending Task Update Button for change the status

    @GetMapping("/employee/edit/{id}")
    public String changePendingTasksStatus(@PathVariable("id") Long id,Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());

        //taskList which are not completed

        model.addAttribute("tasks", taskService.getTasksNotCompleted());

        return "/task/status-update";
    }

    @PostMapping("/employee/update/{id}")
    public String updateTaskStatus(@PathVariable("id") Long id, @ModelAttribute("task") TaskDTO task){

        //First status should be updated

        taskService.updateStatus(task);




        return "redirect:/task/pending";
    }


    //archive tasks : are completed


    @GetMapping("/archive")
    public String getArchiveTasksPage(Model model){

        model.addAttribute("tasks", taskService.getAllTasksAreCompleted());

        return "/task/archive";
    }



}
