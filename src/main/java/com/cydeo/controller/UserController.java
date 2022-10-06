package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create") // UserCreate Page
    public String getUserCreatePage(Model model){
        //user
        model.addAttribute("user",new UserDTO());
        //role
        //model.addAttribute("roles", .....);
        //userList
        //model.addAttribute("users", ....);

        return "/user/create";
    }



}
