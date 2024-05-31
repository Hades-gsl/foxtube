package com.hades.foxtube.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
  private Integer id;

  private String content;

  private Integer createdAt;

  private Integer videoId;

  private Integer authorId;

  public static Comment create(String content, Integer videoId, Integer authorId) {
    return Comment.builder()
        .content(content)
        .createdAt((int) Instant.now().toEpochMilli())
        .videoId(videoId)
        .authorId(authorId)
        .build();
  }
}
