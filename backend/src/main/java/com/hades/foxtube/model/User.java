package com.hades.foxtube.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
public class User {
  private Long id;

  private String username;

  private String email;

  private String password;

  private String avatar;

  private String profile;

  private Long videoCount;

  public static User create(String username, String email, String password, String profile) {
    return User.builder()
        .username(username)
        .email(email)
        .password(password)
        .avatar(_getAvatar())
        .profile(profile)
        .videoCount(0L)
        .build();
  }

  public static String _getAvatar() {
    // response example:
    //    {
    //            "code": 200,
    //            "msg": "获取成功",
    //            "imgurl": "https://tvax3.sinaimg.cn/large/9bd9b167ly1g1p9a84uk9j20b40b4jrq.jpg",
    //            "width": 400,
    //            "height": 400,
    //            "from": "api.t1qq.com"
    //    }
    try {
      URL url =
          URI.create("https://api.t1qq.com/api/tool/sjtx?key=3TXLrhdf3oyFf0Qazs2L7M4J05&type=json")
              .toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.connect();

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
          response.append(inputLine);
        }
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode objectNode = objectMapper.readTree(response.toString());

        return objectNode.get("imgurl").asText();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }
}
