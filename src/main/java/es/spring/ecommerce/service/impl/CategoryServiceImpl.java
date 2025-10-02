package es.spring.ecommerce.service.impl;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.mapper.CategoryMapper;
import es.spring.ecommerce.repository.CategoryRepository;
import es.spring.ecommerce.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto create(CategoryDto entity) {
        return mapper.toDto(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public CategoryDto update(CategoryDto entity) {
        return mapper.toDto(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
