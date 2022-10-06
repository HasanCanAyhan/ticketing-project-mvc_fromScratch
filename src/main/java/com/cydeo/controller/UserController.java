package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/show") // UserCreate Page
    public String getUserCreatePage(Model model){
        //user
        model.addAttribute("user",new UserDTO());
        //role
        model.addAttribute("roles", roleService.readAll());
        //userList
        model.addAttribute("users", userService.readAll());

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user ){

        userService.save(user);


        return "redirect:/user/show";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);

        return "redirect:/user/show";

    }


    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user", userService.findById(username));
        model.addAttribute("roles", roleService.readAll());
        model.addAttribute("users", userService.readAll());

        return "/user/update";

    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){

        userService.update(user);

        return "redirect:/user/show";

    }




}
