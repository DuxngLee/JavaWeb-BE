package com.myweb.weblaptop.controller.admin;

import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.myweb.weblaptop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          UploadService uploadService,
                          PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

//    @RequestMapping("/")
//    private String getHomePage(Model model) {
//        List<User> users = userService.getAllUserByEmail("1@gmail.com");
//        System.out.println(users);
//        model.addAttribute("testModel", "test");
//        model.addAttribute("testAttribute", "from Controller with model");
//        return "home";
//    }

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
                                  @ModelAttribute("newUser") @Valid User user,
                                  BindingResult newUserBindingResult,
                                  @RequestParam("avatarFile") MultipartFile file) {
        //validate user input
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        if (newUserBindingResult.hasErrors())
        {
            return "admin/user/create";
        }
        //

        String avatar = this.uploadService.handleUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));

        userService.handleSaveUser(user);
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
