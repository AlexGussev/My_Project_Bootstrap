package ru.alexgussev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.alexgussev.entity.Role;
import ru.alexgussev.entity.User;
import ru.alexgussev.service.RoleService;
import ru.alexgussev.service.UserService;

import java.util.List;
import java.util.HashSet;
import java.util.Set;


@RestController
public class RestUserController {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RestUserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "allUsers", headers="Accept=application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("admin")
    public @ResponseBody List<User> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(newSet(user.getRoles()));
        userService.saveUser(user);
        return userService.getAllUsers();
    }

    @PutMapping("admin")
    public @ResponseBody List<User> editUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(newSet(user.getRoles()));
        userService.saveUser(user);
        return userService.getAllUsers();
    }

    @GetMapping("admin/{id}")
    public User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "roles", headers="Accept=application/json")
    public Set<Role> allRoles() {
        return roleService.getAll();
    }

    @DeleteMapping("admin/{id}")
    public @ResponseBody List<User> deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return userService.getAllUsers();
    }

    @GetMapping("/userInfo")
    public User userInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Set<Role> newSet(Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            if (role.getName() != null) {
                roleSet.add(roleService.findRoleByName(role.getName()));
            }
        }
        return roleSet;
    }
}
