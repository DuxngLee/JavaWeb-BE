package com.myweb.weblaptop.service;

import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUser()
    {
        return this.userRepository.findAll();
    }

    public User getUserById(long id){
        return this.userRepository.findById(id);
    }

    public List<User> getAllUserByEmail(String email)
    {
        return this.userRepository.findAll();
    }

    public User handleSaveUser(User user)
    {
        return this.userRepository.save(user);
    }

    public User deleteUserById(long id)
    {
        return this.userRepository.findById(id);
    }
}
