package es.spring.ecommerce.service;

import es.spring.ecommerce.entity.Product;
import es.spring.ecommerce.repository.ProductRepository;
import es.spring.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setStock(10);
        product1.setPrice(999.99);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");
        product2.setStock(25);
        product2.setPrice(29.99);
    }

    @Test
    void testAddStock_Success() {
        // Given
        Long productId = 1L;
        int stockToAdd = 5;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setName("Laptop");
        expectedProduct.setStock(15);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        // When
        Product result = productService.addStock(productId, stockToAdd);

        // Then
        assertNotNull(result);
        assertEquals(15, result.getStock());
        verify(productRepository).findById(productId);
        verify(productRepository).save(product1);
        assertEquals(15, product1.getStock());
    }

    @Test
    void testAddStock_ProductNotFound() {
        // Given
        Long productId = 999L;
        int stockToAdd = 5;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.addStock(productId, stockToAdd));

        assertEquals("Producto no encontrado", exception.getMessage());
        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testRemoveStock_Success() {
        // Given
        Long productId = 1L;
        int stockToRemove = 3;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setStock(7);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        // When
        Product result = productService.removeStock(productId, stockToRemove);

        // Then
        assertNotNull(result);
        assertEquals(7, result.getStock());
        verify(productRepository).findById(productId);
        verify(productRepository).save(product1);
        assertEquals(7, product1.getStock());
    }

    @Test
    void testRemoveStock_StockCannotGoNegative() {
        // Given
        Long productId = 1L;
        int stockToRemove = 15;

        when(productRepository.findById(productId)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        // When
        Product result = productService.removeStock(productId, stockToRemove);

        // Then
        assertNotNull(result);
        assertEquals(0, product1.getStock());
        verify(productRepository).findById(productId);
        verify(productRepository).save(product1);
    }

    @Test
    void testRemoveStock_ProductNotFound() {
        // Given
        Long productId = 999L;
        int stockToRemove = 5;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.removeStock(productId, stockToRemove));

        assertEquals("Producto no encontrado", exception.getMessage());
        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testFindByName_Found() {
        // Given
        String productName = "laptop";
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // When
        Product result = productService.findByName(productName);

        // Then
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(1L, result.getId());
        verify(productRepository).findAll();
    }

    @Test
    void testFindByName_NotFound() {
        // Given
        String productName = "Keyboard";
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // When
        Product result = productService.findByName(productName);

        // Then
        assertNull(result);
        verify(productRepository).findAll();
    }

    @Test
    void testFindByName_EmptyList() {
        // Given
        String productName = "Laptop";
        List<Product> products = List.of();

        when(productRepository.findAll()).thenReturn(products);

        // When
        Product result = productService.findByName(productName);

        // Then
        assertNull(result);
        verify(productRepository).findAll();
    }

    @Test
    void testCreate_Success() {
        // Given
        Product newProduct = new Product();
        newProduct.setName("Teclado");
        newProduct.setStock(15);
        newProduct.setPrice(45.99);

        Product savedProduct = new Product();
        savedProduct.setId(3L);
        savedProduct.setName("Teclado");
        savedProduct.setStock(15);
        savedProduct.setPrice(45.99);

        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        // When
        Product result = productService.create(newProduct);

        // Then
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("Teclado", result.getName());
        verify(productRepository).save(newProduct);
    }

    @Test
    void testFindAll_Success() {
        // Given
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        // When
        List<Product> result = productService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());
        verify(productRepository).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        // Given
        List<Product> products = List.of();
        when(productRepository.findAll()).thenReturn(products);

        // When
        List<Product> result = productService.findAll();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    void testFindById_Found() {
        // Given
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product1));

        // When
        Product result = productService.findById(productId);

        // Then
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Laptop", result.getName());
        verify(productRepository).findById(productId);
    }

    @Test
    void testFindById_NotFound() {
        // Given
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.findById(productId));

        assertEquals("Producto no encontrado", exception.getMessage());
        verify(productRepository).findById(productId);
    }

    @Test
    void testUpdate_Success() {
        // Given
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Laptop Gaming");
        updatedProduct.setStock(8);
        updatedProduct.setPrice(1299.99);

        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        // When
        Product result = productService.update(updatedProduct);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop Gaming", result.getName());
        assertEquals(8, result.getStock());
        verify(productRepository).existsById(1L);
        verify(productRepository).save(updatedProduct);
    }

    @Test
    void testUpdate_ProductNotFound() {
        // Given
        Product updatedProduct = new Product();
        updatedProduct.setId(999L);
        updatedProduct.setName("Producto Inexistente");

        when(productRepository.existsById(999L)).thenReturn(false);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.update(updatedProduct));

        assertEquals("Producto no encontrado", exception.getMessage());
        verify(productRepository).existsById(999L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDelete_Success() {
        // Given
        Long productId = 1L;

        // When
        productService.delete(productId);

        // Then
        verify(productRepository).deleteById(productId);
    }

    @Test
    void testDelete_VerifyOnlyDeleteByIdCalled() {
        // Given
        Long productId = 2L;

        // When
        productService.delete(productId);

        // Then
        verify(productRepository, times(1)).deleteById(productId);
        verifyNoMoreInteractions(productRepository);
    }
}