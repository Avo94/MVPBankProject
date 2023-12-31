package org.telran.bankproject.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.bankproject.com.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//
}