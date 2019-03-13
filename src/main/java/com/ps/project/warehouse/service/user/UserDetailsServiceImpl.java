package com.ps.project.warehouse.service.user;

import com.ps.project.warehouse.Repository.UserRepository;
import com.ps.project.warehouse.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserBuilder userBuilder;
        if(user != null){
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRolesNames());
            if(!userBuilder.build().isEnabled() || !user.isEnabled()){
                throw new DisabledException("User is disabled...");
            }
        }else {
            throw new UsernameNotFoundException("User not found");
        }
        return userBuilder.build();

    }
}
