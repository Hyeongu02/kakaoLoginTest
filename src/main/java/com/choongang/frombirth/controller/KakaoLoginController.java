package com.choongang.frombirth.controller;

import com.choongang.frombirth.command.KakaoUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        System.out.println("콜백실핼ㄹㄹ되");
        // Kakao에서 Access Token 받기
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // Access Token을 사용하여 사용자 정보 받기
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        redirectAttributes.addFlashAttribute("userInfo", userInfo);

        // 'common/loginTest' 페이지로 리다이렉트
        return "redirect:/common/loginTest";
    }
}