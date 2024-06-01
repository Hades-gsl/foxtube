package com.hades.foxtube.service.impl;

import com.hades.foxtube.dao.VideoDao;
import com.hades.foxtube.model.Video;
import com.hades.foxtube.service.VideoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Service
public class VideoServiceImpl implements VideoService {
  private VideoDao videoDao;
  private final Long LIMIT = 12L;

  @Autowired
  public VideoServiceImpl(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public List<Video> getVideos(Long authorId, Long offset) {
    return videoDao.getVideos(authorId, offset, LIMIT);
  }

  @Override
  public void insertVideo(Video video) {
    videoDao.insertVideo(video);
  }

  @Override
  public Long getVideoCount(Long authorId) {
    return videoDao.getVideoCount(authorId);
  }

  @Override
  public Video getVideo(Long id) {
    return videoDao.getVideo(id);
  }
}
