package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.RegisterRequestDto;
import com.hades.foxtube.dto.UserDto;
import com.hades.foxtube.model.User;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toUserDto(User user);

  User toUser(UserDto userDto);

  List<UserDto> toUserDtoList(List<User> users);

  List<User> toUserList(List<UserDto> userDtos);

  User toUser(RegisterRequestDto registerRequestDto);
}
