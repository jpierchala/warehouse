package com.ps.project.warehouse.controller;

import com.ps.project.warehouse.Repository.RoleRepository;
import com.ps.project.warehouse.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(path = "/create")
    public String create(Model model){
        model.addAttribute("role", new Role());
        return("role/create");
    }

    @PostMapping(path = "/save")
    public String save(@Valid @ModelAttribute("role") Role role, BindingResult result){
        if(result.hasErrors()){
            return("role/create");
        }
        roleRepository.save(role);

        return "redirect:/";
    }
}
