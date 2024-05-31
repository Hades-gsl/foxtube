package com.hades.foxtube.controller;

import com.hades.foxtube.api.LikeApi;
import com.hades.foxtube.dto.LikeDto;
import com.hades.foxtube.mapper.LikeMapper;
import com.hades.foxtube.model.Like;
import com.hades.foxtube.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
  public ResponseEntity<LikeDto> getLike(Integer videoId, Integer authorId, String authorization) {
    return ResponseEntity.ok(likeMapper.toLikeDto(likeService.getLike(videoId, authorId)));
  }

  @Override
  public ResponseEntity<Object> deleteLike(
      Integer id, Integer videoId, Integer authorId, String authorization) {
    likeService.deleteLike(new Like(id, videoId, authorId));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Object> addLike(String authorization, Integer videoId, Integer authorId) {
    Like like = Like.create(videoId, authorId);
    likeService.insertLike(like);
    return ResponseEntity.created(URI.create("/like/" + like.getId())).build();
  }
}
