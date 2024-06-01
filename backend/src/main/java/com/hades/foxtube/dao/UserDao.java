package com.hades.foxtube.dao;

import com.hades.foxtube.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface UserDao {
  User getUserByName(String username);

  void insertUser(User user);

  User getUserById(Long id);

  User getUserByEmail(String email);
}
