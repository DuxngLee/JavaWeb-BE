package com.myweb.weblaptop.repository;

import com.myweb.weblaptop.domain.Order;
import com.myweb.weblaptop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsers(User user);
}
