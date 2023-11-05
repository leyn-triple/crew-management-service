package com.triple.crew.dto.response;

import com.triple.crew.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
  private final String username;
  private final String imgUrl;

  public UserResponseDto(User user) {
    this.username = user.getUsername();
    this.imgUrl = user.getImgUrl();
  }
}