package com.triple.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UserRequestDto {
  private String username;
  private MultipartFile image;
}