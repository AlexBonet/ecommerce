package es.spring.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface IController<T> {
    @Operation(summary = "Create a new entity")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Entity created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    T create(T entity);


    @Operation(summary = "Find all entities")
    @ApiResponse(responseCode = "200", description = "Entity found successfully")
    List<T> findAll();

    @Operation(summary = "Find entity by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entities found successfully"),
            @ApiResponse(responseCode = "404", description = "No entities found")
    })
    T findById(Long id);

    @Operation(summary = "Update an entity")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
    })
    T update(Long id, T entity);

    @Operation(summary = "Delete an entity")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Entity deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
    })
    void delete(Long id);
}