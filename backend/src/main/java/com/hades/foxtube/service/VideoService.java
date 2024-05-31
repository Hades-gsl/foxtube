package com.hades.foxtube.service;

import com.hades.foxtube.model.Video;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface VideoService {
  List<Video> getVideos(Integer authorId, Integer offset);

  void insertVideo(Video video);

  Integer getVideoCount(Integer authorId);

  Video getVideo(Integer id);
}
