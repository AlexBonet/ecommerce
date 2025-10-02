package es.spring.ecommerce.controller.impl;

import es.spring.ecommerce.controller.ICategoryController;
import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryControllerImpl implements ICategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryControllerImpl.class);

    private final ICategoryService service;

    public CategoryControllerImpl(ICategoryService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto entity) {
        log.info("Creating category with name {}", entity.getName());
        return service.create(entity);
    }

    @Override
    @GetMapping
    public List<CategoryDto> findAll() {
        log.info("Finding all categories");
        return service.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        log.info("Finding category with id {}", id);
        return service.findById(id);
    }

    @Override
    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto entity) {
        log.info("Updating category with id {}", id);
        return service.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting category with id {}", id);
        service.delete(id);
    }
}
