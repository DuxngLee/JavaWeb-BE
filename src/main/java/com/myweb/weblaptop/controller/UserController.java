package com.myweb.weblaptop.controller;

import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.repository.UserRepository;
import org.springframework.ui.Model;
import com.myweb.weblaptop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    private String getHomePage(Model model)
    {
        List<User> users = userService.getAllUserByEmail("1@gmail.com");
        System.out.println(users);
        model.addAttribute("testModel", "test");
        model.addAttribute("testAttribute", "from Controller with model");
        return "home";
    }

    @RequestMapping("/admin/user")
    private String getUserPage(Model model)
    {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/createTable";
    }

    @RequestMapping("/admin/user/create") //GET
    private String getCreateUserPage(Model model)
    {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    private String createUserPage(Model model, @ModelAttribute("newUser") User user)
    {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }


}
