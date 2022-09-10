package ru.alexgussev.service;



import ru.alexgussev.entity.Role;

import java.util.Set;

public interface RoleService {
    public Set<Role> getAll();
    Role findRoleByName(String role);

    Set<Role> getRole(long id);

}
