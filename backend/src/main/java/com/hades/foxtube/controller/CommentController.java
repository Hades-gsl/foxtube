package com.hades.foxtube.controller;

import com.hades.foxtube.api.CommentApi;
import com.hades.foxtube.dto.CommentDto;
import com.hades.foxtube.dto.CountDto;
import com.hades.foxtube.mapper.CommentMapper;
import com.hades.foxtube.model.Comment;
import com.hades.foxtube.service.CommentService;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@RestController
public class CommentController implements CommentApi {
  private CommentService commentService;
  private CommentMapper commentMapper;

  @Autowired
  public CommentController(CommentService commentService, CommentMapper commentMapper) {
    this.commentService = commentService;
    this.commentMapper = commentMapper;
  }

  @Override
  public ResponseEntity<Object> addComment(
      String authorization, String content, Integer videoId, Integer authorId) {
    Comment comment = Comment.create(content, videoId, authorId);
    commentService.insertComment(comment);
    return ResponseEntity.created(URI.create("/comment/" + comment.getId())).build();
  }

  @Override
  public ResponseEntity<List<CommentDto>> getComments(
      Integer offset, Integer videoId, String authorization) {
    return ResponseEntity.ok(
        commentMapper.toCommentDtoList(commentService.getComments(videoId, offset)));
  }

  @Override
  public ResponseEntity<CountDto> getCommentsCount(Integer videoId, String authorization) {
    return ResponseEntity.ok(new CountDto().count(commentService.getCommentCount(videoId)));
  }
}
