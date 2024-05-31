package com.hades.foxtube.controller;

import com.hades.foxtube.api.UserApi;
import com.hades.foxtube.dto.UserDto;
import com.hades.foxtube.mapper.UserMapper;
import com.hades.foxtube.model.User;
import com.hades.foxtube.security.service.UserDetailsImpl;
import com.hades.foxtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@RestController
public class UserController implements UserApi {
  private UserService userService;
  private UserMapper userMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @Override
  public ResponseEntity<UserDto> getUser(String authorization) {
    return ResponseEntity.ok(
        userMapper.toUserDto(
            ((UserDetailsImpl)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser()));
  }

  @Override
  public ResponseEntity<UserDto> login(String email, String password, String authorization) {
    User user = userService.login(email, password);

    if (user == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(userMapper.toUserDto(user));
  }

  @Override
  public ResponseEntity<Object> register(
      String username, String email, String password, String authorization, String profile) {

    User user = User.create(username, email, password, profile);

    userService.insertUser(user);

    return ResponseEntity.created(URI.create("/user/" + user.getId())).build();
  }
}
