package ru.alexgussev.service;

import ru.alexgussev.entity.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public User getUserById(Long id);
    public void deleteUserById(Long id);
    public List<User> getAllUsers();
}
