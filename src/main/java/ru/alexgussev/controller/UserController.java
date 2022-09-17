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
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers() {
        return "admin";
    }

    @GetMapping("/user")
    public String info() {
        return "info";
    }
}
