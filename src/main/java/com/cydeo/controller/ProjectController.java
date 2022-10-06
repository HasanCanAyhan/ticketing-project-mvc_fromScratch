package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);
        return "redirect:/project/show";

    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode,Model model){

        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("managers", userService.getManagers());
        model.addAttribute("projects",projectService.readAll() );


        return "/project/update";
    }

    @PostMapping("/update")//save button for update
    public String updateProject(@ModelAttribute("project") ProjectDTO project){

        //status should be set  before update bcs there is no this in the form
        projectService.update(project);

        return "redirect:/project/show";
    }


    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){

        //status should be converted to Complete - new method for that in the projectService

        projectService.completeById(projectCode);

        return "redirect:/project/show";

    }


    //PROJECT-STATUS PAGE

    @GetMapping("/project-status")
    public String getProjectStatusPage(Model model){

        //projectCode/projectName/start-EndDate/Manager/Unfinished-Completed/Status/Action

        UserDTO manager = userService.findById("john@cydeo.com");

        List<ProjectDTO> projects_theManager = projectService.getProjectsForSpecificManager(manager);

        model.addAttribute("projects", projects_theManager);

        return "/manager/project-status";
    }




}
