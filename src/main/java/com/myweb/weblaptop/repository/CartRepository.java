package com.myweb.weblaptop.repository;

import com.myweb.weblaptop.domain.Cart;
import com.myweb.weblaptop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
