package com.hades.foxtube.mapper;

import com.hades.foxtube.dao.VideoDao;
import com.hades.foxtube.dto.VideoDto;
import com.hades.foxtube.model.Video;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface VideoMapper {
  VideoDto toVideoDto(Video video);

  Video toVideo(VideoDto videoDto);

  List<VideoDto> toVideoDtoList(List<Video> videos);

  List<Video> toVideoList(List<VideoDto> videoDtos);
}
