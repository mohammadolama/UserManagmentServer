package com.example.usermanagmentserver.repository;

import com.example.usermanagmentserver.entity.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser , Long> {


    Optional<AbstractUser> findByUserName(String username);

    Optional<AbstractUser> findByEmail(String email);
}
