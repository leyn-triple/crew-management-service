package com.triple.crew.controller;

import com.triple.crew.dto.request.SocialLoginRequestDto;
import com.triple.crew.dto.response.JwtResponse;
import com.triple.crew.service.UserService;
import com.triple.crew.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetailsService userDetailsService;
  private final UserService userService;

  /**
   * 유저 카카오 로그인 API
   */
  @PostMapping(value = "/login/kakao")
  public ResponseEntity<?> createAuthenticationTokenByKakao(
      @RequestBody SocialLoginRequestDto socialLoginDto) throws Exception {
    String kakaoId = userService.kakaoLogin(socialLoginDto.getToken());
    final UserDetails userDetails = userDetailsService.loadUserByUsername(kakaoId);
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
  }
}