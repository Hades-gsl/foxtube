package com.hades.foxtube.security.service;

import com.hades.foxtube.model.User;
import com.hades.foxtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: Hades @Date: 2024/5/30 @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserService userService;

  @Autowired
  public UserDetailsServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getUser(username);
    return UserDetailsImpl.build(user);
  }
}
