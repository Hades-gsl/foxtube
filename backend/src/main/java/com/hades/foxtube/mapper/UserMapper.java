package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.UserDto;
import com.hades.foxtube.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface UserMapper {
  UserDto toUserDto(User user);

  User toUser(UserDto userDto);

  List<UserDto> toUserDtoList(List<User> users);

  List<User> toUserList(List<UserDto> userDtos);
}
