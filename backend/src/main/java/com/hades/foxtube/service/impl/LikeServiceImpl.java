package com.hades.foxtube.service.impl;

import com.hades.foxtube.dao.LikeDao;
import com.hades.foxtube.model.Like;
import com.hades.foxtube.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Service
public class LikeServiceImpl implements LikeService {
  private LikeDao likeDao;

  @Autowired
  public void setLikeDao(LikeDao likeDao) {
    this.likeDao = likeDao;
  }

  @Override
  public void insertLike(Like like) {
    likeDao.insertLike(like);
  }

  @Override
  public void deleteLike(Like like) {
    likeDao.deleteLike(like);
  }

  @Override
  public Like getLike(int userId, int videoId) {
    return likeDao.getLike(userId, videoId);
  }
}
