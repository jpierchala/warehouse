package com.ps.project.warehouse.config.bootstrap;

import com.ps.project.warehouse.Repository.RoleRepository;
import com.ps.project.warehouse.Repository.UserRepository;
import com.ps.project.warehouse.domain.Role;
import com.ps.project.warehouse.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class BootstrapAdminUserAndRoles implements InitializingBean {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Environment environment;

    @Transactional
    @Override
    public void afterPropertiesSet() {
        log.info("Bootstrapping roles...");
        createRoles();
        log.info("Bootstrapping admin user.");
        createAdmin();
        createTrainee();
        log.info("Bootstrapping done.");
    }

    public void createRoles(){
        Role role;
        if(roleRepository.findByName("USER") == null){
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
        if (roleRepository.findByName("ADMIN") == null){
            role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }

    }

    public void createAdmin(){
        createSpecifiedUser("manager", "admin");
    }

    public void createTrainee(){
        createSpecifiedUser("warehouseman", "trainee");
    }

    private void createSpecifiedUser(String username, String role){
        if(userRepository.findByUsername(username.toLowerCase()) != null){
            log.info(username.toLowerCase() + " user already exists");
            return;
        }
        User user = new User();
        user.setUsername(username.toLowerCase());
        user.setEmail("jpierchala@pm.me");

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(role.toUpperCase()));
        if(roles.isEmpty()){
            log.info("NO " + role.toUpperCase() + " ROLE IN DATABASE!");
            return;
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(username.toLowerCase()));
        userRepository.save(user);
    }
}
