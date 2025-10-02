package es.spring.ecommerce.service;

import es.spring.ecommerce.dto.CategoryDto;
import es.spring.ecommerce.entity.Category;
import es.spring.ecommerce.mapper.CategoryMapper;
import es.spring.ecommerce.repository.CategoryRepository;
import es.spring.ecommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    private CategoryServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CategoryServiceImpl(repository, mapper);
    }

    @Test
    void create_mapsDtoToEntity_saves_andReturnsMappedDto() {
        CategoryDto inputDto = mock(CategoryDto.class);
        Category toSave = mock(Category.class);
        Category saved = mock(Category.class);
        CategoryDto outputDto = mock(CategoryDto.class);

        when(mapper.toEntity(inputDto)).thenReturn(toSave);
        when(repository.save(toSave)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(outputDto);

        CategoryDto result = service.create(inputDto);

        assertSame(outputDto, result);

        InOrder inOrder = inOrder(mapper, repository);
        inOrder.verify(mapper).toEntity(inputDto);
        inOrder.verify(repository).save(toSave);
        inOrder.verify(mapper).toDto(saved);

        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findAll_mapsAllEntitiesToDtos() {
        Category e1 = new Category(1L, "Electrónica", List.of());
        Category e2 = new Category(2L, "Hogar", List.of());

        CategoryDto d1 = new CategoryDto(1L, "Electrónica", List.of());
        CategoryDto d2 = new CategoryDto(2L, "Hogar", List.of());

        when(repository.findAll()).thenReturn(List.of(e1, e2));
        when(mapper.toDto(e1)).thenReturn(d1);
        when(mapper.toDto(e2)).thenReturn(d2);

        List<CategoryDto> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(d1, result.get(0));
        assertSame(d2, result.get(1));

        verify(repository).findAll();
        verify(mapper).toDto(e1);
        verify(mapper).toDto(e2);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findAll_returnsEmptyList_whenRepositoryReturnsEmpty() {
        when(repository.findAll()).thenReturn(List.of());

        List<CategoryDto> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository).findAll();
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findById_returnsMappedDto_whenCategoryExists() {
        Long id = 42L;
        Category entity = mock(Category.class);
        CategoryDto dto = mock(CategoryDto.class);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        CategoryDto result = service.findById(id);

        assertSame(dto, result);
        verify(repository).findById(id);
        verify(mapper).toDto(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findById_returnsNull_whenCategoryDoesNotExist() {
        Long id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.toDto(isNull())).thenReturn(null);

        CategoryDto result = service.findById(id);

        assertNull(result);
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void update_mapsDtoToEntity_saves_andReturnsMappedDto() {
        CategoryDto inputDto = mock(CategoryDto.class);
        Category toSave = mock(Category.class);
        Category saved = mock(Category.class);
        CategoryDto outputDto = mock(CategoryDto.class);

        when(mapper.toEntity(inputDto)).thenReturn(toSave);
        when(repository.save(toSave)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(outputDto);

        CategoryDto result = service.update(inputDto);

        assertSame(outputDto, result);
        verify(mapper).toEntity(inputDto);
        verify(repository).save(toSave);
        verify(mapper).toDto(saved);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void delete_invokesRepositoryDeleteById() {
        Long id = 7L;

        service.delete(id);

        verify(repository).deleteById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }
}
