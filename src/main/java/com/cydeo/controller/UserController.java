package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create") // UserCreate Page
    public String getUserCreatePage(Model model){
        //user
        model.addAttribute("user",new UserDTO());
        //role
        model.addAttribute("roles", roleService.readAll());
        //userList
        model.addAttribute("users", userService.readAll());

        return "/user/create";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") UserDTO user){

        userService.save(user);

        return "redirect:/user/create";

    }



}
