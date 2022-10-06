package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping("/show") // project create page
    public String getProjectCreatePage(Model model){
        //project object
        model.addAttribute("project",new ProjectDTO());
        //assignedManager
        model.addAttribute("managers", userService.getManagers());
        //projectList
        model.addAttribute("projects",projectService.readAll() );


        return "/project/create";
    }


    @PostMapping("/insert")//save button and submit form
    public String saveProject(@ModelAttribute("project") ProjectDTO project){

        //status does not exist in the project list

        projectService.save(project);


        return "redirect:/project/show";
    }



}
