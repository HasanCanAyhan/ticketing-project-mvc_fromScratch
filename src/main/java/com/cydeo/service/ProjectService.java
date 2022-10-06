package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CRUDService<String, ProjectDTO>{

    void completeById(String projectCode);

    List<ProjectDTO> getProjectsForSpecificManager(UserDTO manager);


}
