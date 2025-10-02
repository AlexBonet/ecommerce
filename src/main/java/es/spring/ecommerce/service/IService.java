package es.spring.ecommerce.service;

import java.util.List;

public interface IService<T> {
    T create(T entity);

    List<T> findAll();

    T findById(Long id);

    T update(T entity);

    void delete(Long id);
}
