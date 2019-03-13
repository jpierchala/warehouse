package com.ps.project.warehouse.controller;

import com.ps.project.warehouse.Repository.RoleRepository;
import com.ps.project.warehouse.Repository.UserRepository;
import com.ps.project.warehouse.domain.User;
import com.ps.project.warehouse.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl userService;


    @GetMapping(path = "/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleRepository.findAll());
        return "user/create";
    }

    @PostMapping(path = "/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "user/create";
        }
        userService.saveUser(user);
        return "redirect:create";
    }
}
