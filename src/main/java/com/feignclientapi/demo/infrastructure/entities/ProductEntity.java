package com.feignclientapi.demo.infrastructure.entities;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "ProductEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity{
    @Id
    private String id;
    @Column(length = 800)
    private String title;
    @Column(precision = 6, scale = 2)
    private BigDecimal price;
    @Column(length = 800)
    private String category;
    @Column(length = 800)
    private String description;
    @Column(length = 800)
    private String image;
    private LocalDateTime creationTime;
    private LocalDateTime updateTime;
}
