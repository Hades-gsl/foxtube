package com.hades.foxtube.service.impl;

import com.hades.foxtube.dao.VideoDao;
import com.hades.foxtube.model.Video;
import com.hades.foxtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Service
public class VideoServiceImpl implements VideoService {
  private VideoDao videoDao;
  private final Integer LIMIT = 12;

  @Autowired
  public VideoServiceImpl(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public List<Video> getVideos(Integer authorId, Integer offset) {
    return videoDao.getVideos(authorId, offset, LIMIT);
  }

  @Override
  public void insertVideo(Video video) {
    videoDao.insertVideo(video);
  }

  @Override
  public Integer getVideoCount(Integer authorId) {
    return videoDao.getVideoCount(authorId);
  }

  @Override
  public Video getVideo(Integer id) {
    return videoDao.getVideo(id);
  }
}
