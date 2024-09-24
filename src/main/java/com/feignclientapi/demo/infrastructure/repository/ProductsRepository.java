package com.feignclientapi.demo.infrastructure.repository;

import com.feignclientapi.demo.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, String> {

    Boolean existsByTitle(String title);

    ProductEntity findByTitle(String title);

    void deleteByTitle(String title);
}
