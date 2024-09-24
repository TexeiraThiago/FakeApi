package com.feignclientapi.demo.business.mapper;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import com.feignclientapi.demo.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class ProductMapper {

    public ProductEntity toEntity(ProductDTO dto) {
        return ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .title(dto.title().trim())
                .price(dto.price())
                .category(dto.category())
                .description(dto.description())
                .image(dto.image())
                .creationTime(LocalDateTime.now())
                .build();
    }

    public ProductDTO toProductDTO(ProductEntity productEntity) {
        return new ProductDTO(Math.abs(new Random().nextLong()),
                productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getPrice(),
                productEntity.getCategory(),
                productEntity.getDescription(),
                productEntity.getImage());
    }

    public ProductEntity toUpdateEntity(ProductDTO dto, ProductEntity productEntity) {
        return  ProductEntity.builder()
                .id(productEntity.getId())
                .title(dto.title() != null ? dto.title() : productEntity.getTitle())
                .price(dto.price() != null ? dto.price() :productEntity.getPrice())
                .category(dto.category() != null ? dto.category() : productEntity.getCategory())
                .description(dto.description() != null ? dto.description() : productEntity.getDescription())
                .image(dto.image() != null ? dto.image() : productEntity.getImage())
                .creationTime(productEntity.getCreationTime())
                .updateTime(LocalDateTime.now())
                .build();
    }
}
