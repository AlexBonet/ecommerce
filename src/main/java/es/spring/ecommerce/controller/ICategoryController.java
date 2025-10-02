package es.spring.ecommerce.controller;

import es.spring.ecommerce.dto.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category Controller")
public interface ICategoryController extends IController<CategoryDto> {
}
