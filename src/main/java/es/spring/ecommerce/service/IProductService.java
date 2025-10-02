package es.spring.ecommerce.service;

import es.spring.ecommerce.entity.Product;

public interface IProductService extends IService<Product> {
    Product addStock(Long id, int stock);

    Product removeStock(Long id, int stock);

    Product findByName(String name);
}
