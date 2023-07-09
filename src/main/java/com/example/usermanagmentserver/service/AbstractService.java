package com.example.usermanagmentserver.service;

import com.example.usermanagmentserver.exception.AbstractException;

public interface AbstractService<T , ID> {


    void preCreateConditionCheck(T t) throws AbstractException;
}
