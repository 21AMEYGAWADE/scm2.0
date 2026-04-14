package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    //extra db related methods

    //custom query methods

    //custom finder methods
}
