package com.triple.crew.entity;

import com.triple.crew.util.UserValidator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  @Column(nullable = false)
  private String kakaoId;

  @Column(nullable = false)
  private String imgUrl;

  /**
   * 카카오 로그인 유저 생성자
   */
  public User(String username, String email, UserRole role, String kakaoId, String imgUrl) {
    UserValidator.validateCreateUser(username, email, role, kakaoId, imgUrl);
    this.username = username;
    this.email = email;
    this.role = role;
    this.kakaoId = kakaoId;
    this.imgUrl = imgUrl;
  }

  /**
   * 유저 정보 수정 메서드
   */
  public void updateUserImg(String imgUrl) {
    UserValidator.validateUpdateImg(imgUrl);
    this.imgUrl = imgUrl;
  }

  public void updateUsername(String username) {
    UserValidator.validateUpdateName(username);
    this.username = username;
  }
}