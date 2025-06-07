package com.myweb.weblaptop.controller.admin;

import com.myweb.weblaptop.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String getProduct()
    {
        return "admin/product/showProduct";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model)
    {
        model.addAttribute("newProduct", new Product());
        return "admin/product/createProduct";
    }
}
