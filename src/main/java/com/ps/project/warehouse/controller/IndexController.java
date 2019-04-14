package com.ps.project.warehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping(path = "/")
    public String index(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("page", 0);
        redirectAttributes.addAttribute("size", 10);
        return "redirect:product/list";
    }

}
