package edu.t1.javapro1.hw4.persistence.dao;

import edu.t1.javapro1.hw4.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId);
}
