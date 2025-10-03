package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    private CategoryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CategoryMapper();
    }

    @Test
    void toEntity_shouldMapBasicFields_andIgnoreProducts() {
        CategoryDto dto = new CategoryDto(10L, "Electronics");

        Category entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(10L);
        assertThat(entity.getName()).isEqualTo("Electronics");
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertThat(mapper.toEntity(null)).isNull();
    }

    @Test
    void toDto_shouldMapBasicFields() {
        Category entity = new Category(5L, "Home");

        CategoryDto dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getName()).isEqualTo("Home");
    }

    @Test
    void toDto_shouldReturnNull_whenEntityIsNull() {
        assertThat(mapper.toDto(null)).isNull();
    }
}
