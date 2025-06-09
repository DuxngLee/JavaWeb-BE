package com.myweb.weblaptop.repository;

import com.myweb.weblaptop.domain.Cart;
import com.myweb.weblaptop.domain.CartDetail;
import com.myweb.weblaptop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);
}
