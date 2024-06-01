package com.hades.foxtube.service;

import com.hades.foxtube.model.Video;
import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface VideoService {
  List<Video> getVideos(Long authorId, Long offset);

  void insertVideo(Video video);

  Long getVideoCount(Long authorId);

  Video getVideo(Long id);
}
