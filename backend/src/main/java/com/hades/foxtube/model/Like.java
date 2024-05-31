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
public class Like {
  private Integer id;

  private Integer videoId;

  private Integer userId;

  public static Like create(Integer videoId, Integer userId) {
    return Like.builder().videoId(videoId).userId(userId).build();
  }
}
