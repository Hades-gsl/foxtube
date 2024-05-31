package com.hades.foxtube.dao;

import com.hades.foxtube.model.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface LikeDao {
  void insertLike(Like like);

  void deleteLike(Like like);

  Like getLike(int userId, int videoId);
}
