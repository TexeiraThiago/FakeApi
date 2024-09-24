package com.feignclientapi.demo.infrastructure.client;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "fake-api", url = "${fake-api.url}")
public interface FakeApiClient {

    @GetMapping("/products")
    List<ProductDTO> getAllProducts();
}
