package edu.t1.javapro1.hw4.service.impl;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateUserRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.UserResponse;
import edu.t1.javapro1.hw4.mapper.UserMapper;
import edu.t1.javapro1.hw4.persistence.dao.UserRepository;
import edu.t1.javapro1.hw4.persistence.model.User;
import edu.t1.javapro1.hw4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.mapCreateUserRequestToUser(createUserRequest);
        userRepository.save(user);

        return userMapper.mapUserToUserResponse(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public UserResponse findUserResponseById(Long id) {
        return userMapper.mapUserToUserResponse(this.findUserById(id));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::mapUserToUserResponse).toList();
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
