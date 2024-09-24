package com.feignclientapi.demo.apiv1.controller;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import com.feignclientapi.demo.business.service.FakeApiService;
import com.feignclientapi.demo.business.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Fake-Api")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;


    @Operation(summary = "Fetch all products From the API ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "fetch succeded"),
            @ApiResponse(responseCode = "500", description = "Error during the process")
    })
    @Transactional
    @PostMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.fetchAllProducts());
    }

    @Operation(summary = "Save One Product ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "save sucssesfully"),
            @ApiResponse(responseCode = "500", description = "Error during saving")
    })
    @Transactional
    @PostMapping
    public ResponseEntity<ProductDTO> saveOneProductDTO(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.saveOneProductDTO(dto));
    }

    @Operation(summary = "Get one product by name from the data base", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product succeded"),
            @ApiResponse(responseCode = "500", description = "Error during the retreiving")
    })
    @GetMapping(value = "{name}")
    public ResponseEntity<ProductDTO> getOneByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @Operation(summary = "Get All product", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product succeded"),
            @ApiResponse(responseCode = "500", description = "Error during the retreiving")
    })
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @Operation(summary = "Delete one product by name from the data base", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete succeded"),
            @ApiResponse(responseCode = "500", description = "Error during the deletion")
    })
    @DeleteMapping(value = "{name}")
    public ResponseEntity<Void> deteByName(@PathVariable(value = "name") String name) {
        productService.deleteByName(name);
        return ResponseEntity.accepted().build();
    }
    @Operation(summary = "Update one Product", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update succeded"),
            @ApiResponse(responseCode = "500", description = "Error during the update")
    })
    @PutMapping(value = "{id}")
    public ResponseEntity<ProductDTO> upDateOneProduct(@PathVariable(value = "id") String id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateOneProduct(id, dto));
    }


}
