package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.CommentDto;
import com.hades.foxtube.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper
public interface CommentMapper {
  CommentDto toCommentDto(Comment comment);

  Comment toComment(CommentDto commentDto);

  List<CommentDto> toCommentDtoList(List<Comment> comments);

  List<Comment> toCommentList(List<CommentDto> commentDtos);
}
