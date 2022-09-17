package ru.alexgussev.service;

import ru.alexgussev.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    List<User> getAllUsers();
}
