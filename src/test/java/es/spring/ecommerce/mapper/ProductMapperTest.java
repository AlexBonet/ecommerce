package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.ProductDto;
import es.spring.ecommerce.entity.Category;
import es.spring.ecommerce.entity.Product;
import es.spring.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @Mock
    private CategoryRepository categoryRepository;

    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper(categoryRepository);
    }

    @Test
    void toEntity_withCategoryId_resolvesCategory() {
        // given
        ProductDto dto = new ProductDto(1L, "Mouse", 19.99, 7, 10L);
        Category cat = new Category(10L, "Periféricos", null);
        when(categoryRepository.findById(10L)).thenReturn(Optional.of(cat));

        // when
        Product entity = mapper.toEntity(dto);

        // then
        assertEquals(1L, entity.getId());
        assertEquals("Mouse", entity.getName());
        assertEquals(19.99, entity.getPrice());
        assertEquals(7, entity.getStock());
        assertNotNull(entity.getCategory());
        assertEquals(10L, entity.getCategory().getId());
    }

    @Test
    void toEntity_withoutCategoryId_setsNullCategory() {
        // given
        ProductDto dto = new ProductDto(2L, "Teclado", 29.50, 5, null);

        // when
        Product entity = mapper.toEntity(dto);

        // then
        assertEquals(2L, entity.getId());
        assertEquals("Teclado", entity.getName());
        assertEquals(29.50, entity.getPrice());
        assertEquals(5, entity.getStock());
        assertNull(entity.getCategory());
    }

    @Test
    void toDto_mapsAllFields() {
        // given
        Category cat = new Category(3L, "Gaming", null);
        Product entity = new Product(9L, "Headset", 49.90, 12, cat);

        // when
        ProductDto dto = mapper.toDto(entity);

        // then
        assertEquals(9L, dto.getId());
        assertEquals("Headset", dto.getName());
        assertEquals(49.90, dto.getPrice());
        assertEquals(12, dto.getStock());
        assertEquals(3L, dto.getCategoryId());
    }

    @Test
    void toDto_allowsNullCategory() {
        // given
        Product entity = new Product(9L, "Headset", 49.90, 12, null);

        // when
        ProductDto dto = mapper.toDto(entity);

        // then
        assertEquals(9L, dto.getId());
        assertNull(dto.getCategoryId());
    }
}
