package com.example.marketinall.domain.dto;

import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.Role;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequestDto {

    @Email(message = "email 형식으로 기입해주세요.")
    private String email;

    @NotNull
    private String nickname;

    @Size(min = 8, max = 16, message = "password의 길이는 8~16자리로 해주세요.")
    private String password;
    private Role role;


    @Builder
    public SignupRequestDto(String email, String nickname, String password, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = Role.USER;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
