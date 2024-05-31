package com.hades.foxtube;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@SpringBootApplication
@MapperScan("com.hades.foxtube.dao")
public class FoxtubeApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoxtubeApplication.class, args);
  }
}
