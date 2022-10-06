package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;

import java.util.List;

public class UserServiceImpl extends AbstractMapServiceDB<String,UserDTO> implements UserService {

    @Override
    public void save(UserDTO user) {
        super.save(user.getUserName(), user);
    }

    @Override
    public List<UserDTO> readAll() {
        return super.readAll();
    }

    @Override
    public UserDTO findById(String username) {
        return super.findById(username);
    }

    @Override
    public void deleteById(String username) {
        super.deleteById(username);
    }

    @Override
    public void update(UserDTO user) {
        super.update(user.getUserName(),user);
    }
}
