package com.example.usermanagmentserver.service;

import com.example.usermanagmentserver.entity.AbstractUser;
import com.example.usermanagmentserver.exception.AbstractException;
import com.example.usermanagmentserver.exception.CreationFailedException;
import org.springframework.stereotype.Service;

public interface UserService extends AbstractService<AbstractUser, Long> {

    public AbstractUser create(AbstractUser abstractUser) throws AbstractException;

    public AbstractUser update(AbstractUser abstractUser) throws AbstractException;

    public AbstractUser delete(Long userId) throws AbstractException;

    public AbstractUser get(Long userId) throws AbstractException;


}
