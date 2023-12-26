package com.example.marketinall.controller.api;

import com.example.marketinall.config.auth.PrincipalDetails;
import com.example.marketinall.domain.dto.SignupRequestDto;
//import com.example.marketinall.config.jwt.JwtToken;
import com.example.marketinall.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody SignupRequestDto dto) {
        Long memberId = memberService.signUp(dto);
        log.info(dto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }
}
