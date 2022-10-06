package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CRUDService<String, UserDTO>{

    List<UserDTO> getManagers();
    List<UserDTO> getEmployees();

}
