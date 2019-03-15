package com.ps.project.warehouse.controller;

import com.ps.project.warehouse.Repository.RoleRepository;
import com.ps.project.warehouse.Repository.UserRepository;
import com.ps.project.warehouse.domain.Role;
import com.ps.project.warehouse.domain.User;
import com.ps.project.warehouse.domain.UserEditCommand;
import com.ps.project.warehouse.exceptions.UserDoesNotExistException;
import com.ps.project.warehouse.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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
        return "userish/create";
    }

    @PostMapping(path = "/save")
    public String save(RedirectAttributes redirectAttributes, @RequestParam long roleId, @Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "userish/create";
        }
        Set<Role> roles = new HashSet<>();
        if(roleRepository.existsById(roleId)){
            roles.add(roleRepository.getOne(roleId));
        }else {
            roles.add(roleRepository.findByName("USER"));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "User has been created");
        return "redirect:list";
    }

    @GetMapping(path = "/edit")
    public String editUser(Model model, @RequestParam long userId){
        if(userRepository.existsById(userId)){
           User user = userRepository.getOne(userId);
            model.addAttribute("user", user);
            model.addAttribute("roles", roleRepository.findAll());
        }else{
            throw new UserDoesNotExistException();
        }
        return "userish/edit";
    }

    @PostMapping(path = "/edit")
    public String postEditUser(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("user") UserEditCommand userEditCommand, BindingResult result){
        if(result.hasErrors()){
            return "userish/edit";
        }

        if(userRepository.existsById(userEditCommand.getId())){
           User user = userRepository.getOne(userEditCommand.getId());

            Set<Role> roles = new HashSet<>();
            if(roleRepository.existsById(userEditCommand.getRoleId())){
                roles.add(roleRepository.getOne(userEditCommand.getRoleId()));
            }else {
                roles.add(roleRepository.findByName("USER"));
            }
            user.setRoles(roles);
            user.setUsername(userEditCommand.getUsername());
            user.setEmail(userEditCommand.getEmail());

            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "User has been saved");
            return "redirect:list";
        }else{
            throw new UserDoesNotExistException();
        }
    }

    @GetMapping(path = "/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @RequestParam long userId){
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            redirectAttributes.addFlashAttribute("success", "User has been deleted");
        }else {
            throw new UserDoesNotExistException();
        }
        return "redirect:list";
    }

    @GetMapping(path = "/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userish/list";
    }

}
