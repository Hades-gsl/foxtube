package com.hades.foxtube.service.impl;

import com.hades.foxtube.dao.CommentDao;
import com.hades.foxtube.model.Comment;
import com.hades.foxtube.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {
  private CommentDao commentDao;
  private final Integer COMMENT_LIMIT = 10;

  @Autowired
  public CommentServiceImpl(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Override
  public void insertComment(Comment comment) {
    commentDao.insertComment(comment);
  }

  @Override
  public List<Comment> getComments(Integer videoId, Integer offset) {
    return commentDao.getComments(videoId, offset, COMMENT_LIMIT);
  }

  @Override
  public Integer getCommentCount(Integer videoId) {
    return commentDao.getCommentCount(videoId);
  }
}
