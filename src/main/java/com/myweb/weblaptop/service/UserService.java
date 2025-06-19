package com.myweb.weblaptop.service;

import com.myweb.weblaptop.domain.Role;
import com.myweb.weblaptop.domain.User;
import com.myweb.weblaptop.domain.dto.RegisterDTO;
import com.myweb.weblaptop.repository.OrderRepository;
import com.myweb.weblaptop.repository.ProductRepository;
import com.myweb.weblaptop.repository.RoleRepository;
import com.myweb.weblaptop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       ProductRepository productRepository,
                       OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }

    public boolean checkEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }
    public long countProducts() {
        return this.productRepository.count();
    }
    public long countOrders() {
        return this.orderRepository.count();
    }

    public Page<User> getAllUsers(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Transactional
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

}
