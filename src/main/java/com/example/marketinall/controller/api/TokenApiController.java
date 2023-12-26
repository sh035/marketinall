package com.example.marketinall.controller.api;

import com.example.marketinall.domain.dto.CreateAccessTokenRequestDto;
import com.example.marketinall.domain.dto.CreateAccessTokenResponseDto;
import com.example.marketinall.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseDto> createNewAccessToken(@RequestBody CreateAccessTokenRequestDto requestDto) {
        String newAccessToken = tokenService.createNewAccessToken(requestDto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponseDto(newAccessToken));
    }
}
