package com.triple.crew.service;

import com.triple.crew.entity.User;
import com.triple.crew.entity.UserRole;
import com.triple.crew.repository.UserRepository;
import com.triple.crew.security.KakaoOAuth2;
import com.triple.crew.security.KakaoUserInfo;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final KakaoOAuth2 kakaoOAuth2;

  @Transactional
  public String kakaoLogin(String token) {
    // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
    KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(token);
    String kakaoId = userInfo.getId();
    String nickname = userInfo.getNickname();
    String email = userInfo.getEmail();
    String defaultimgUrl = "";

    // DB 에 중복된 Kakao Id 가 있는지 확인
    User kakaoUser = userRepository.findByKakaoId(kakaoId)
        .orElse(null);

    // 카카오 정보로 회원가입
    if (kakaoUser == null) {
      kakaoUser = new User(nickname, email, UserRole.USER, kakaoId, defaultimgUrl);
      userRepository.save(kakaoUser);
    }

    return kakaoId;
  }
}