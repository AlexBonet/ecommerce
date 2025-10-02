package es.spring.ecommerce.mapper;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.dto.ProductDto;
import es.spring.ecommerce.entity.Category;
import es.spring.ecommerce.entity.Product;
import es.spring.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        CategoryRepository repo = mock(CategoryRepository.class);
        when(repo.findById(100L)).thenReturn(java.util.Optional.of(new Category(100L, "Periféricos", null)));
        when(repo.findById(200L)).thenReturn(java.util.Optional.of(new Category(200L, "Componentes", null)));

        ProductMapper productMapper = new ProductMapper(repo);
        categoryMapper = new CategoryMapper(productMapper);
    }

    @Test
    void toEntity_mapsNestedProducts() {
        // given
        ProductDto pd1 = new ProductDto(1L, "Ratón", 19.99, 7, 100L);
        ProductDto pd2 = new ProductDto(2L, "Teclado", 29.50, 5, 200L);
        CategoryDto dto = new CategoryDto(10L, "Accesorios", List.of(pd1, pd2));

        // when
        Category entity = categoryMapper.toEntity(dto);

        // then
        assertEquals(10L, entity.getId());
        assertEquals("Accesorios", entity.getName());
        assertNotNull(entity.getProducts());
        assertEquals(2, entity.getProducts().size());

        Product p1 = entity.getProducts().get(0);
        assertEquals(1L, p1.getId());
        assertEquals("Ratón", p1.getName());
        assertEquals(19.99, p1.getPrice());
        assertEquals(7, p1.getStock());
        assertNotNull(p1.getCategory());
        assertEquals(100L, p1.getCategory().getId());

        Product p2 = entity.getProducts().get(1);
        assertEquals(2L, p2.getId());
        assertEquals("Teclado", p2.getName());
        assertEquals(29.50, p2.getPrice());
        assertEquals(5, p2.getStock());
        assertNotNull(p2.getCategory());
        assertEquals(200L, p2.getCategory().getId());
    }

    @Test
    void toDto_mapsNestedProductDtos() {
        // given
        Category cat100 = new Category(100L, "Periféricos", null);
        Category cat200 = new Category(200L, "Componentes", null);

        Product p1 = new Product(1L, "Ratón", 19.99, 7, cat100);
        Product p2 = new Product(2L, "Teclado", 29.50, 5, cat200);
        Category entity = new Category(10L, "Accesorios", List.of(p1, p2));

        // when
        CategoryDto dto = categoryMapper.toDto(entity);

        // then
        assertEquals(10L, dto.getId());
        assertEquals("Accesorios", dto.getName());
        assertNotNull(dto.getProductDtos());
        assertEquals(2, dto.getProductDtos().size());

        ProductDto pd1 = dto.getProductDtos().get(0);
        assertEquals(1L, pd1.getId());
        assertEquals("Ratón", pd1.getName());
        assertEquals(19.99, pd1.getPrice());
        assertEquals(7, pd1.getStock());
        assertEquals(100L, pd1.getCategoryId());

        ProductDto pd2 = dto.getProductDtos().get(1);
        assertEquals(2L, pd2.getId());
        assertEquals("Teclado", pd2.getName());
        assertEquals(29.50, pd2.getPrice());
        assertEquals(5, pd2.getStock());
        assertEquals(200L, pd2.getCategoryId());
    }

    @Test
    void toDto_handlesNullOrEmptyProducts() {
        // given
        Category entity = new Category(11L, "Vacía", null);

        // when
        CategoryDto dto = categoryMapper.toDto(entity);

        // then
        assertEquals(11L, dto.getId());
        assertNotNull(dto.getProductDtos());
        assertTrue(dto.getProductDtos().isEmpty());
    }

    @Test
    void toEntity_handlesNullOrEmptyProductDtos() {
        // given
        CategoryDto dto = new CategoryDto(12L, "Sin productos", null);

        // when
        Category entity = categoryMapper.toEntity(dto);

        // then
        assertEquals(12L, entity.getId());
        assertNotNull(entity.getProducts());
        assertTrue(entity.getProducts().isEmpty());
    }
}
