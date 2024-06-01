package com.hades.foxtube.service;

import com.hades.foxtube.model.User;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface UserService {
  User getUser(String username);

  void insertUser(User user);

  User getUser(Long id);

  User login(String email, String password);
}
