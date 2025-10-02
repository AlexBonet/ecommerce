package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.ProductDto;
import es.spring.ecommerce.entity.Category;
import es.spring.ecommerce.entity.Product;
import es.spring.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapper<Product, ProductDto> {
    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        }
        return new Product(dto.getId(), dto.getName(), dto.getPrice(), dto.getStock(), category);
    }

    @Override
    public ProductDto toDto(Product entity) {
        Long categoryId = entity.getCategory() != null ? entity.getCategory().getId() : null;
        return new ProductDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getStock(), categoryId);
    }
}
