package com.feignclientapi.demo.apiv1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.math.BigDecimal;

public record ProductDTO(
        @JsonIgnore
        Long id,
        String entity_id,
        String title,
        BigDecimal price,
        String category,
        String description,
        String image) {
}
