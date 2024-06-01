package com.hades.foxtube.dao;

import com.hades.foxtube.model.Video;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface VideoDao {
  List<Video> getVideos(Long authorId, Long offset, Long limit);

  void insertVideo(Video video);

  Long getVideoCount(Long authorId);

  Video getVideo(Long id);
}
