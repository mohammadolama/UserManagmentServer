package com.example.usermanagmentserver.service.impl;

import com.example.usermanagmentserver.entity.AbstractUser;
import com.example.usermanagmentserver.exception.AbstractException;
import com.example.usermanagmentserver.exception.CreationFailedException;
import com.example.usermanagmentserver.exception.EntityNotFoundException;
import com.example.usermanagmentserver.exception.UpdateFailedException;
import com.example.usermanagmentserver.repository.UserRepository;
import com.example.usermanagmentserver.service.UserService;
import jdk.jfr.consumer.RecordedThread;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void preCreateConditionCheck(AbstractUser abstractUser) throws CreationFailedException {
        if (abstractUser.getId() != null ||
                StringUtils.isEmpty(abstractUser.getUserName()) ||
                StringUtils.isEmpty(abstractUser.getFirstName()) ||
                StringUtils.isEmpty(abstractUser.getLastName()) ||
                StringUtils.isEmpty(abstractUser.getEmail())) {
            throw new CreationFailedException("Preconditions are not met.");
        }

        Optional<AbstractUser> optional = userRepository.findByUserName(abstractUser.getUserName());
        if (optional.isPresent()){
            throw new CreationFailedException("Username already exists.");
        }
        Optional<AbstractUser> byEmail = userRepository.findByEmail(abstractUser.getEmail());
        if (byEmail.isPresent()){
            throw new CreationFailedException("Email already exists.");
        }
    }


    @Override
    public void preUpdateConditionCheck(AbstractUser abstractUser, Long id) throws AbstractException {
        if (abstractUser.getId() == null || !Objects.equals(abstractUser.getId(), id)){
            throw new UpdateFailedException("Preconditions are not met.");
        }
        Optional<AbstractUser> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new EntityNotFoundException("with id " + id);
        }else {
            AbstractUser user = byId.get();
            if (StringUtils.isNotEmpty(abstractUser.getEmail()) && !user.getEmail().equals(abstractUser.getEmail())){
                Optional<AbstractUser> byEmail = userRepository.findByEmail(abstractUser.getEmail());
                if (byEmail.isPresent()){
                    throw new UpdateFailedException("Email already exists.");
                }
            }
        }

    }


    @Override
    public AbstractUser create(AbstractUser abstractUser) throws AbstractException {
        preCreateConditionCheck(abstractUser);
        AbstractUser save = userRepository.save(abstractUser);
        return save;
    }

    @Override
    public AbstractUser update(long id, AbstractUser abstractUser) throws AbstractException {
        preUpdateConditionCheck(abstractUser , id);
        Optional<AbstractUser> byId = userRepository.findById(id);
        AbstractUser entity = byId.get();
        entity.update(abstractUser);
        AbstractUser save = userRepository.save(entity);
        return save;

    }

    @Override
    public AbstractUser delete(Long userId) throws EntityNotFoundException {
        Optional<AbstractUser> byId = userRepository.findById(userId);
        if (byId.isEmpty()){
            throw new EntityNotFoundException("with id " + userId);
        }
        userRepository.delete(byId.get());
        return byId.get();
    }

    @Override
    public AbstractUser get(Long userId) throws EntityNotFoundException {
        Optional<AbstractUser> byId = userRepository.findById(userId);
        if (byId.isPresent()){
            return byId.get();
        }else {
            throw new EntityNotFoundException("with id " + userId);
        }
    }

}
