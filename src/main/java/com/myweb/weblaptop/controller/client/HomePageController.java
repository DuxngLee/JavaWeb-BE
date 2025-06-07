package com.myweb.weblaptop.controller.client;

import com.myweb.weblaptop.domain.Product;
import com.myweb.weblaptop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.fetchProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }
}
