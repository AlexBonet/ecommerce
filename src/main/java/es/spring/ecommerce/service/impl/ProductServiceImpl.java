package es.spring.ecommerce.service.impl;

import es.spring.ecommerce.entity.Product;
import es.spring.ecommerce.repository.ProductRepository;
import es.spring.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product addStock(Long id, int stock) {
        Product product = findById(id);
        product.setStock(product.getStock() + stock);
        return repository.save(product);
    }

    @Override
    public Product removeStock(Long id, int stock) {
        Product product = findById(id);
        int newStock = product.getStock() - stock;
        product.setStock(Math.max(newStock, 0));
        return repository.save(product);
    }

    @Override
    public Product findByName(String name) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product create(Product entity) {
        return repository.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Product update(Product entity) {
        if (!repository.existsById(entity.getId())) {
            throw new RuntimeException("Producto no encontrado");
        }
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
