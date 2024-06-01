package com.hades.foxtube.dao;

import com.hades.foxtube.model.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface CommentDao {
  void insertComment(Comment comment);

  List<Comment> getComments(Long videoId, Long offset, Long limit);

  Long getCommentCount(Long videoId);
}
