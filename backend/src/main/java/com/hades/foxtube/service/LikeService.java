package com.hades.foxtube.service;

import com.hades.foxtube.model.Like;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface LikeService {
  void insertLike(Like like);

  void deleteLike(Long id);

  Like getLike(Long userId, Long videoId);
}
