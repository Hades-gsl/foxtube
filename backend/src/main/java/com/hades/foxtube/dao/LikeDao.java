package com.hades.foxtube.dao;

import com.hades.foxtube.model.Like;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface LikeDao {
  void insertLike(Like like);

  void deleteLike(Long id);

  Like getLike(Long userId, Long videoId);
}
