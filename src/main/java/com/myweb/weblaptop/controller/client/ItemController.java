package com.myweb.weblaptop.controller.client;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @GetMapping("/product/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        return "client/product/detail";
    }

}
