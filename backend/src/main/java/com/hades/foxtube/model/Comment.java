package com.hades.foxtube.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
  private Long id;

  private String content;

  private Long createdAt;

  private Long videoId;

  private Long authorId;

  public static Comment create(String content, Long videoId, Long authorId) {
    return Comment.builder()
        .content(content)
        .createdAt(Instant.now().toEpochMilli())
        .videoId(videoId)
        .authorId(authorId)
        .build();
  }
}
