package com.hades.foxtube.model;

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
public class Video {
  private Integer id;

  private String title;

  private String cover;

  private String url;

  private String description;

  private Integer publishedAt;

  private Integer likes;

  private Integer authorId;

  public static Video create(
      String title, String cover, String url, String description, Integer authorId) {
    return Video.builder()
        .title(title)
        .cover(cover)
        .url(url)
        .description(description)
        .publishedAt((int) System.currentTimeMillis())
        .likes(0)
        .authorId(authorId)
        .build();
  }
}
