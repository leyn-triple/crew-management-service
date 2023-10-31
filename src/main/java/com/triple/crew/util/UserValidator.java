package com.triple.crew.util;

import com.triple.crew.entity.UserRole;
import com.triple.crew.exception.ApiRequestException;

public class UserValidator {

  /**
   * 유저 생성 validation
   */
  public static void validateCreateUser(String username, String email, UserRole role, String kakaoId, String imgUrl) {
    if (username == null || username.trim().isEmpty()) {
      throw new ApiRequestException("입력된 유저 이름이 없습니다.");
    }

    if (username.length() > 8 || username.isEmpty()) {
      throw new ApiRequestException("유저 이름은 1~8자 사이로 입력하세요.");
    }

    if (email == null) {
      throw new ApiRequestException("이메일이 없습니다.");
    }

    if (role == null) {
      throw new ApiRequestException("유저 권한이 없습니다.");
    }

    if (kakaoId == null || kakaoId.trim().isEmpty()) {
      throw new ApiRequestException("카카오 아이디 값이 없습니다.");
    }

    if (!URLValidator.urlValidator(imgUrl)) {
      throw new ApiRequestException("이미지 링크를 확인해주세요.");
    }
  }

  /**
   * 유저 이름 변경 validation
   */
  public static void validateUpdateName(String username) {
    if (username == null || username.trim().isEmpty()) {
      throw new ApiRequestException("입력된 유저 이름이 없습니다.");
    }

    if (username.length() > 8 || username.isEmpty()) {
      throw new ApiRequestException("유저 이름은 1~8자 사이로 입력하세요.");
    }
  }

  /**
   * 유저 이미지 변경 validation
   */
  public static void validateUpdateImg(String imgUrl) {
    if (!URLValidator.urlValidator(imgUrl)) {
      throw new ApiRequestException("이미지 링크를 확인해주세요.");
    }
  }
}