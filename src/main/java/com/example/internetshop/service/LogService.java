package com.example.internetshop.service;

public interface LogService<T> {
    T persistUserAction(T t);
}
