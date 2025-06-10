package com.myweb.weblaptop.controller.admin;

import com.myweb.weblaptop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserService userService;
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashBoard(Model model)
    {
        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.userService.countProducts());
        model.addAttribute("countOrders", this.userService.countOrders());
        return "admin/dashboard/show";
    }
}
