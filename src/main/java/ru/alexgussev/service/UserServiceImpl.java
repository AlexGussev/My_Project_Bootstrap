package ru.alexgussev.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexgussev.entity.User;
import ru.alexgussev.repository.UserRepository;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public void saveUser(User user) {
            userRepository.save(user);
    }
    @Override
    @Transactional
    public User getUserById(Long id) {
       return userRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
