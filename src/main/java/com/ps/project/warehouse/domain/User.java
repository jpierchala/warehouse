package com.ps.project.warehouse.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private Long roleId;


    public String[] getRolesNames() {
        ArrayList<String> rolesList = new ArrayList<>();
        roles.forEach(role -> rolesList.add(role.toString()));
        String[] rolesNames = new String[rolesList.size()];
        rolesList.toArray(rolesNames);
        return rolesNames;
    }
}