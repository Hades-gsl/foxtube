package com.hades.foxtube.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hades.foxtube.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author: Hades @Date: 2024/5/30 @Description:
 */
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private User user;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(User user, Collection<? extends GrantedAuthority> authorities) {
    this.user = user;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = new ArrayList<>();

    return new UserDetailsImpl(user, authorities);
  }

  public User getUser() {
    return user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(this.user.getId(), user.user.getId());
  }
}
