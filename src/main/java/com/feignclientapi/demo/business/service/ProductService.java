package com.feignclientapi.demo.business.service;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import com.feignclientapi.demo.business.mapper.ProductMapper;
import com.feignclientapi.demo.infrastructure.entities.ProductEntity;
import com.feignclientapi.demo.infrastructure.exception.BusinessException;
import com.feignclientapi.demo.infrastructure.exception.ConflictException;
import com.feignclientapi.demo.infrastructure.exception.UnprocessableEntityException;
import com.feignclientapi.demo.infrastructure.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductsRepository repository;
    private final ProductMapper mapper;

    public ProductEntity saveOneProduct(ProductEntity productEntity) {
        try {

            return repository.save(productEntity);
        } catch (BusinessException e) {
            throw new BusinessException("Error during save product", e);
        }
    }

    public ProductDTO saveOneProductDTO(ProductDTO dto) {
        try {

            ProductEntity productEntity = mapper.toEntity(dto);
            if (isTitleExist(dto.title()).equals(true)) {
                throw new ConflictException(format("Product %s already exist in database", dto.title()));
            }
            return mapper.toProductDTO(repository.save(productEntity));
        } catch (BusinessException e) {
            throw new BusinessException("Error during save product");
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    public List<ProductDTO> getAllProducts() {
        try {
            return repository.findAll().stream().map(mapper::toProductDTO).toList();
        } catch (BusinessException e) {
            throw new BusinessException("Error during saving all products", e);
        }
    }

    public Boolean isTitleExist(String title) {
        try {
            return repository.existsByTitle(title);
        } catch (UnsupportedOperationException e) {
            throw new UnprocessableEntityException(format("Product %s does not exist", title));
        }
    }

    public ProductDTO getByName(String name) {
        try {
            ProductEntity productEntity = repository.findByTitle(name);
            if (Objects.isNull(productEntity)) {
                throw new UnprocessableEntityException(format("Product %s does not exist", name));
            }
            return mapper.toProductDTO(productEntity);
        } catch (BusinessException e) {
            throw new BusinessException(format("Error during during search by name: %s", name));
        } catch (UnsupportedOperationException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByName(String name) {
        try {
            Boolean titleExist = isTitleExist(name);
            if (titleExist.equals(false)) {
                throw new UnprocessableEntityException(format("Product %s does not exist", name));
            }
            repository.deleteByTitle(name);
        } catch (BusinessException e) {
            throw new BusinessException(format("Error during deletion %s", name));
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Transactional
    public ProductDTO updateOneProduct(String id, ProductDTO dto) {
        try {
            ProductEntity productById = repository.findById(id).orElseThrow(() -> new UnprocessableEntityException("Id does not exist in database"));
            ProductEntity updateEntity = mapper.toUpdateEntity(dto, productById);
            return mapper.toProductDTO(saveOneProduct(updateEntity));
        } catch (BusinessException e) {
            throw new BusinessException(format("Error during product update: %s", dto.title()));
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }
}
