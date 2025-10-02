package es.spring.ecommerce.controller.impl;

import es.spring.ecommerce.controller.IProductController;
import es.spring.ecommerce.dto.ProductDto;
import es.spring.ecommerce.mapper.ProductMapper;
import es.spring.ecommerce.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements IProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductControllerImpl.class);

    private final IProductService service;
    private final ProductMapper mapper;

    public ProductControllerImpl(IProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PatchMapping("add-stock/{id}")
    @Override
    public ProductDto addStock(@PathVariable Long id, @RequestBody Integer stock) {
        log.info("Adding stock to product with id {}", id);
        return mapper.toDto(service.addStock(id, stock));
    }

    @Override
    @PatchMapping("remove-stock/{id}")
    public ProductDto removeStock(@PathVariable Long id, @RequestBody Integer stock) {
        log.info("Removing stock from product with id {}", id);
        return mapper.toDto(service.removeStock(id, stock));
    }

    @Override
    @GetMapping("/{name}")
    public ProductDto findByName(@PathVariable String name) {
        log.info("Finding product with name {}", name);
        return mapper.toDto(service.findByName(name));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto entity) {
        log.info("Creating product with name {}", entity.getName());
        return mapper.toDto(service.create(mapper.toEntity(entity)));
    }

    @Override
    @GetMapping
    public List<ProductDto> findAll() {
        log.info("Finding all products");
        return service.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        log.info("Finding product with id {}", id);
        return mapper.toDto(service.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto entity) {
        log.info("Updating product with id {}", id);
        return mapper.toDto(service.update(mapper.toEntity(entity)));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting product with id {}", id);
        service.delete(id);
    }
}
