package com.hades.foxtube.service;

import com.hades.foxtube.model.Comment;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
public interface CommentService {
  void insertComment(Comment comment);

  List<Comment> getComments(Integer videoId, Integer offset);

  Integer getCommentCount(Integer videoId);
}
