package com.hades.foxtube.mapper;

import com.hades.foxtube.dto.AddCommentRequestDto;
import com.hades.foxtube.dto.CommentDto;
import com.hades.foxtube.model.Comment;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
  CommentDto toCommentDto(Comment comment);

  Comment toComment(CommentDto commentDto);

  List<CommentDto> toCommentDtoList(List<Comment> comments);

  List<Comment> toCommentList(List<CommentDto> commentDtos);

  Comment toComment(AddCommentRequestDto addCommentRequestDto);
}
