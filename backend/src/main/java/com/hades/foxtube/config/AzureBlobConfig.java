package com.hades.foxtube.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Hades @Date: 2024/5/31 @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "azure.blob")
@Data
public class AzureBlobConfig {
  private String accountName;

  private String sasToken;

  private String containerName;

  @Bean
  public BlobContainerClient getBlobContainerClient() {
    // Append the container name to the URI
    String endpointString =
        String.format("https://%s.blob.core.windows.net/%s", accountName, containerName);

    return new BlobContainerClientBuilder()
        .endpoint(endpointString)
        .sasToken(sasToken)
        .buildClient();
  }
}
