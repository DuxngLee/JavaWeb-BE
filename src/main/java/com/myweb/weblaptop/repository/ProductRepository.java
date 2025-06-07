package com.myweb.weblaptop.repository;

import com.myweb.weblaptop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface can be used to define custom query methods for Product entities.
    // For example, you can add methods like findByName, findByCategory, etc.
    // JpaRepository provides basic CRUD operations out of the box.
}
