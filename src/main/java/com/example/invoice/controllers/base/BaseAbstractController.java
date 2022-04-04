package com.example.invoice.controllers.base;

public abstract class BaseAbstractController<T> {

    protected T service;

    public BaseAbstractController(T service) {
        this.service = service;
    }

}
