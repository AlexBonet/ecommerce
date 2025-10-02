package es.spring.ecommerce.controller;

import es.spring.ecommerce.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Controller")
public interface IProductController extends IController<ProductDto> {
    @Operation(summary = "Add stock to a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid stock value")
    })
    ProductDto addStock(Long id, Integer stock);

    @Operation(summary = "Remove stock from a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock removed successfully"),
            @ApiResponse(responseCode = "400", description = "Insufficient stock")
    })
    ProductDto removeStock(Long id, Integer stock);

    @Operation(summary = "Find a product by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ProductDto findByName(String name);
}