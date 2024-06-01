package com.hades.foxtube.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.hades.foxtube.api.VideoApi;
import com.hades.foxtube.dto.CountDto;
import com.hades.foxtube.dto.VideoDto;
import com.hades.foxtube.mapper.VideoMapper;
import com.hades.foxtube.model.Video;
import com.hades.foxtube.service.VideoService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Hades @Date: 2024/5/29 @Description:
 */
@RestController
public class VideoController implements VideoApi {
  private VideoService videoService;
  private VideoMapper videoMapper;
  private BlobContainerClient blobContainerClient;

  @Autowired
  public VideoController(
      VideoService videoService, VideoMapper videoMapper, BlobContainerClient blobContainerClient) {
    this.videoService = videoService;
    this.videoMapper = videoMapper;
    this.blobContainerClient = blobContainerClient;
  }

  @Override
  public ResponseEntity<Object> addVideo(
      String authorization,
      String contentType,
      String title,
      MultipartFile video,
      MultipartFile cover,
      String description,
      Long authorId) {
    try {
      String videoUrl = upload(video);
      String coverUrl = upload(cover);
      Video video1 = Video.create(title, coverUrl, videoUrl, description, authorId);
      videoService.insertVideo(video1);

      return ResponseEntity.created(URI.create("/video/" + video1.getId())).build();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ResponseEntity.badRequest().build();
  }

  @Override
  public ResponseEntity<VideoDto> getVideo(Long id, String authorization) {
    Video video = videoService.getVideo(id);

    if (video == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(videoMapper.toVideoDto(video));
  }

  @Override
  public ResponseEntity<List<VideoDto>> getVideos(
      Long offset, Long authorId, String authorization) {
    return ResponseEntity.ok(videoMapper.toVideoDtoList(videoService.getVideos(authorId, offset)));
  }

  @Override
  public ResponseEntity<CountDto> getVideosCount(Long authorId, String authorization) {
    return ResponseEntity.ok(new CountDto().count(videoService.getVideoCount(authorId)));
  }

  public String upload(MultipartFile file) throws IOException {
    String blobName = UUID.randomUUID().toString();
    BlobClient blobClient = blobContainerClient.getBlobClient(blobName);

    ByteArrayInputStream dataStream = new ByteArrayInputStream(file.getBytes());
    blobClient.upload(dataStream, file.getSize());

    return blobClient.getBlobUrl();
  }
}
