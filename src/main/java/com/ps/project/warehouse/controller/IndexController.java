package com.ps.project.warehouse.controller;


import com.ps.project.warehouse.Repository.ProductRepository;
import com.ps.project.warehouse.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/")
    public String index(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("page", 0);
        redirectAttributes.addAttribute("size", 10);
        return "redirect:/product/list";
    }

    @GetMapping(path = "/search")
    public String searchProducts(@RequestParam String query, Pageable pageable, Model model){
        Page<Product> allProducts = productRepository.findByNameContainingOrDescriptionContaining(query, query, pageable);
        model.addAttribute("products", allProducts);
        return "productList";
    }

}
