package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.VideoDto;
import com.hades.foxtube.model.Video;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper(componentModel = "spring")
public interface VideoMapper {
  VideoDto toVideoDto(Video video);

  Video toVideo(VideoDto videoDto);

  List<VideoDto> toVideoDtoList(List<Video> videos);

  List<Video> toVideoList(List<VideoDto> videoDtos);
}
