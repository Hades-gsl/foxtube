package com.hades.foxtube.dao;

import com.hades.foxtube.model.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface VideoDao {
  List<Video> getVideos(Integer authorId, Integer offset, Integer limit);

  void insertVideo(Video video);

  Integer getVideoCount(Integer authorId);

  Video getVideo(Integer id);
}
