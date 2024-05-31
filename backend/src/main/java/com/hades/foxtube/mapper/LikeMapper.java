package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.LikeDto;
import com.hades.foxtube.model.Like;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface LikeMapper {
  LikeDto toLikeDto(Like like);

  Like toLike(LikeDto likeDto);

  List<LikeDto> toLikeDtoList(List<Like> likes);

  List<Like> toLikeList(List<LikeDto> likeDtos);
}
