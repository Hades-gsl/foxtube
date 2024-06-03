package com.hades.foxtube.controller;

import com.hades.foxtube.api.UserApi;
import com.hades.foxtube.dto.LoginRequestDto;
import com.hades.foxtube.dto.LoginResponseDto;
import com.hades.foxtube.dto.RegisterRequestDto;
import com.hades.foxtube.dto.UserDto;
import com.hades.foxtube.mapper.UserMapper;
import com.hades.foxtube.model.User;
import com.hades.foxtube.security.jwt.JwtUtils;
import com.hades.foxtube.security.service.UserDetailsImpl;
import com.hades.foxtube.service.UserService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@RestController
public class UserController implements UserApi {
  private UserService userService;
  private UserMapper userMapper;
  private JwtUtils jwtUtils;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper, JwtUtils jwtUtils) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.jwtUtils = jwtUtils;
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
  public ResponseEntity<LoginResponseDto> login(
      String authorization, LoginRequestDto loginRequestDto) {
    User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

    if (user == null) {
      return ResponseEntity.badRequest().build();
    }

    LoginResponseDto loginResponseDto =
        new LoginResponseDto()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .profile(user.getProfile())
            .avatar(user.getAvatar())
            .videoCount(user.getVideoCount())
            .password(user.getPassword())
            .token(jwtUtils.generateToken(user.getUsername()));

    return ResponseEntity.ok(loginResponseDto);
  }

  @Override
  public ResponseEntity<Object> register(
      String authorization, RegisterRequestDto registerRequestDto) {
    User user = userMapper.toUser(registerRequestDto);
    user.setAvatar(User._getAvatar());

    userService.insertUser(user);

    return ResponseEntity.created(URI.create("/user/" + user.getId())).build();
  }
}
