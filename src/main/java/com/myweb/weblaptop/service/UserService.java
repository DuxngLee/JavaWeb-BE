package com.myweb.weblaptop.service;

import com.myweb.weblaptop.domain.Role;
import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.repository.RoleRepository;
import com.myweb.weblaptop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public Role getRoleByName(String name)
    {
        return this.roleRepository.findByName(name);
    }

    @Transactional
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

}
