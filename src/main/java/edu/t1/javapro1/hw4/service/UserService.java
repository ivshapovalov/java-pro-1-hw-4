package edu.t1.javapro1.hw4.service;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateUserRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.UserResponse;
import edu.t1.javapro1.hw4.persistence.model.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse findUserResponseById(Long id);

    User findUserById(Long id);

    List<UserResponse> findAllUsers();

    boolean deleteUserById(Long id);
}
