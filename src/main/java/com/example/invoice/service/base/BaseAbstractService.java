package com.example.invoice.service.base;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseAbstractService<T extends JpaRepository> {

    protected T repository;

    public BaseAbstractService(T repository) {
        this.repository = repository;
    }

}
