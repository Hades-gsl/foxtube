package com.hades.foxtube.controller;

import com.hades.foxtube.api.LikeApi;
import com.hades.foxtube.dto.AddLikeRequestDto;
import com.hades.foxtube.dto.LikeDto;
import com.hades.foxtube.mapper.LikeMapper;
import com.hades.foxtube.model.Like;
import com.hades.foxtube.service.LikeService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@RestController
public class LikeController implements LikeApi {
  private LikeService likeService;
  private LikeMapper likeMapper;

  @Autowired
  public LikeController(LikeService likeService, LikeMapper likeMapper) {
    this.likeService = likeService;
    this.likeMapper = likeMapper;
  }

  @Override
  public ResponseEntity<LikeDto> getLike(Long videoId, Long authorId, String authorization) {
    Like like = likeService.getLike(videoId, authorId);

    if (like == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(likeMapper.toLikeDto(like));
  }

  @Override
  public ResponseEntity<Object> deleteLike(Long id, String authorization) {
    likeService.deleteLike(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Object> addLike(String authorization, AddLikeRequestDto addLikeRequestDto) {
    Like like = likeMapper.toLike(addLikeRequestDto);
    likeService.insertLike(like);
    return ResponseEntity.created(URI.create("/like/" + like.getId())).build();
  }
}
