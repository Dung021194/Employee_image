package com.example.employeeimage.service;

import java.util.List;

public interface ICoreService<E> {
    List<E> findAll();

    E findById(Long id);

    void save(E e);

    void deleteById(Long id);
}
