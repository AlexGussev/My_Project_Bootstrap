package ru.alexgussev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexgussev.entity.Role;
import ru.alexgussev.entity.User;
import ru.alexgussev.service.RoleService;
import ru.alexgussev.service.UserService;



import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    public final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userAuth", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAll());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name ="allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
            user.setRoles(rolesSet);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "info";
    }
}
