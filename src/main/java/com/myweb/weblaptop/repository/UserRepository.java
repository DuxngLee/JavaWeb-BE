package com.myweb.weblaptop.repository;

import com.myweb.weblaptop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User save(User user);

        List<User> findOneByEmail(String email);

        List<User> findAll();

        User findById(long id);

        boolean existsByEmail(String email);

        User findByEmail(String email);
    }

