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
public class BootstrapProductsAndProductTypes implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}
