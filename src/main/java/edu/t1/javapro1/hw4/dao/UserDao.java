package edu.t1.javapro1.hw4.dao;

import edu.t1.javapro1.hw4.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    boolean deleteById(Long id);
}
