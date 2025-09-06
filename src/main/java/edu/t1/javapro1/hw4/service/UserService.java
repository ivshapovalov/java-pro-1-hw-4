package edu.t1.javapro1.hw4.service;

import edu.t1.javapro1.hw4.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long id);

    List<User> findAll();

    boolean deleteById(Long id);
}
