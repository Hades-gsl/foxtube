package com.hades.foxtube.dao;

import com.hades.foxtube.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface CommentDao {
  void insertComment(Comment comment);

  List<Comment> getComments(Integer videoId, Integer offset, Integer limit);

  Integer getCommentCount(Integer videoId);
}
