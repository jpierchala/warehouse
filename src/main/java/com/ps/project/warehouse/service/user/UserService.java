package com.ps.project.warehouse.service.user;

import com.ps.project.warehouse.domain.User;

public interface UserService {

    void saveUser(User user);
    boolean existByUsername(String username);
    User findByUsername(String username);
}
