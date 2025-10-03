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
        if (dto == null) return null;

        Product product = new Product();
        if (dto.getId() != null) product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.getReferenceById(dto.getCategoryId());
        }
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        if (entity == null) return null;
        Long categoryId = (entity.getCategory() != null) ? entity.getCategory().getId() : null;
        return new ProductDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getStock(), categoryId);
    }
}
