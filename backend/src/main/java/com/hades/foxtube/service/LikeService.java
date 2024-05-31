package com.hades.foxtube.service;

import com.hades.foxtube.model.Like;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface LikeService {
  void insertLike(Like like);

  void deleteLike(Like like);

  Like getLike(int userId, int videoId);
}
