package com.example.marketinall.service;

import com.example.marketinall.domain.dto.SignupRequestDto;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.Role;
import com.example.marketinall.repository.MemberRepository;
import com.example.marketinall.util.exception.ApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    public void join() {
        //given
        SignupRequestDto memberDto = SignupRequestDto.builder()
                .nickname("Lee")
                .email("afg@naver.com")
                .password("1234")
                .role(Role.USER)
                .build();
        //when
        Long memberId = memberService.signUp(memberDto);

        //then
        Assertions.assertThat(memberId).isNotNull();
        Member email = memberRepository.findByEmail("afg@naver.com").orElse(null);
        Assertions.assertThat(email.getNickname()).isEqualTo("Lee");
    }

    @Test
    @DisplayName("회원가입 실패 (이메일 중복)")
    public void duplicate_email_join_fail() {
        //given
        SignupRequestDto memberDTO = SignupRequestDto.builder()
                .nickname("132")
                .password("12")
                .email("12@12")
                .build();
        Long memberId = memberService.signUp(memberDTO);

        //when
        SignupRequestDto memberDTO2 = SignupRequestDto.builder()
                .nickname("12")
                .password("12")
                .email("12@12")
                .build();
        //then
        assertThrows(ApiException.class, () -> {
            Long memberId2 = memberService.signUp(memberDTO2);
        });
    }

    @Test
    @DisplayName("회원가입 실패 (닉네임 중복)")
    public void duplicate_nickname_join_fail() {
        //given
        SignupRequestDto memberDTO = SignupRequestDto.builder()
                .nickname("12").password("12").email("12@12").build();
        Long memberId = memberService.signUp(memberDTO);

        //when
        SignupRequestDto memberDTO2 = SignupRequestDto.builder()
                .nickname("12").password("12").email("13@13").build();

        //then
        assertThrows(ApiException.class, () -> {
            Long memberId2 = memberService.signUp(memberDTO2);
        });
    }
}