package com.feignclientapi.demo.business.service;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import com.feignclientapi.demo.business.mapper.ProductMapper;
import com.feignclientapi.demo.infrastructure.client.FakeApiClient;
import com.feignclientapi.demo.infrastructure.entities.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FakeApiService {

    private final FakeApiClient client;
    private final ProductService productService;
    private final ProductMapper mapper;

    public List<ProductDTO> fetchAllProducts() {
        try {
            List<ProductDTO> productDTOS = client.getAllProducts();
            productDTOS.forEach(productDto -> {
                if (productService.isTitleExist(productDto.title().trim()).equals(false)) {
                    ProductEntity productEntity = mapper.toEntity(productDto);
                    productService.saveOneProduct(productEntity);
                }
            });
            return productService.getAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("Error during products download");
        }
    }
}
