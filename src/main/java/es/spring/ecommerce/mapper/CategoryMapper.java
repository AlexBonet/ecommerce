package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.dto.ProductDto;
import es.spring.ecommerce.entity.Category;
import es.spring.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements IMapper<Category, CategoryDto> {

    private final ProductMapper productMapper;

    @Autowired
    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        List<Product> products = dto.getProductDtos() == null
                ? List.of()
                : dto.getProductDtos().stream().map(productMapper::toEntity).toList();
        return new Category(dto.getId(), dto.getName(), products);
    }

    @Override
    public CategoryDto toDto(Category entity) {
        List<ProductDto> productDtos = entity.getProducts() == null
                ? List.of()
                : entity.getProducts().stream().map(productMapper::toDto).toList();
        return new CategoryDto(entity.getId(), entity.getName(), productDtos);
    }
}
