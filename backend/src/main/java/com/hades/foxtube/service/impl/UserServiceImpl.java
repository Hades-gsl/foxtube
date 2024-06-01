package com.hades.foxtube.service.impl;

import com.hades.foxtube.dao.UserDao;
import com.hades.foxtube.model.User;
import com.hades.foxtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Service
public class UserServiceImpl implements UserService {
  private UserDao userDao;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getUser(String username) {
    return userDao.getUserByName(username);
  }

  @Override
  public void insertUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.insertUser(user);
  }

  @Override
  public User getUser(Long id) {
    return userDao.getUserById(id);
  }

  @Override
  public User login(String email, String password) {
    User user = userDao.getUserByEmail(email);
    if (passwordEncoder.matches(password, user.getPassword())) {
      return user;
    }

    return null;
  }
}
