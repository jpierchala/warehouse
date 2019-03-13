package com.ps.project.warehouse.Repository;


import com.ps.project.warehouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}