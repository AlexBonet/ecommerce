package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements IMapper<Category, CategoryDto> {

    @Override
    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;
        Category category = new Category();
        if (dto.getId() != null) category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        if (entity == null) return null;
        return new CategoryDto(entity.getId(), entity.getName());
    }
}
