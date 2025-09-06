package edu.t1.javapro1.hw4.service;

import edu.t1.javapro1.hw4.dao.UserDao;
import edu.t1.javapro1.hw4.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }
}
