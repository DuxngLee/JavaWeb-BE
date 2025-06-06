package com.myweb.weblaptop.controller.admin;

import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.service.UploadService;
import jakarta.servlet.ServletContext;
import org.springframework.ui.Model;
import com.myweb.weblaptop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UserService userService,
                          UploadService uploadService) {

        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    private String getHomePage(Model model) {
        List<User> users = userService.getAllUserByEmail("1@gmail.com");
        System.out.println(users);
        model.addAttribute("testModel", "test");
        model.addAttribute("testAttribute", "from Controller with model");
        return "home";
    }

    @RequestMapping("/admin/user")
    private String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/createTable";
    }

    @RequestMapping("/admin/user/{id}")
    private String getUserDetailPage(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/show";
    }

    @PostMapping("/admin/user/update")
    public String showUpdateForm(@ModelAttribute("newUser") User user, Model model) {
        User userUpdate = this.userService.getUserById(user.getId());
        if (userUpdate != null) {
            userUpdate.setFullName(user.getFullName());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setAddress(user.getAddress());

            this.userService.handleSaveUser(userUpdate);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") //GET
    private String getUpdateUserPage(Model model, @PathVariable long id) {
        User userUpdate = this.userService.getUserById(id);
        model.addAttribute("user", userUpdate);
        return "admin/user/update";
    }


    @RequestMapping("/admin/user/create") //GET
    private String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    private String createUserPage(Model model,
                                  @ModelAttribute("newUser") User user,
                                  @RequestParam("avatarFile") MultipartFile file) {

        String avatar = this.uploadService.handleUploadFile(file, "avatar");
        //userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }


    @GetMapping("/admin/user/delete/{id}")
    public String showDeletePage(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }


}
