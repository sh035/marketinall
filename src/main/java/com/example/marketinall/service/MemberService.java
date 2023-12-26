package com.example.marketinall.service;

import com.example.marketinall.domain.dto.SignupRequestDto;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.Role;
import com.example.marketinall.repository.MemberRepository;
import com.example.marketinall.util.exception.ApiException;
import com.example.marketinall.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.secret")
    private String secretKey;
    private Long expireTiemMs = 1000 * 60 * 60l; //1시간


    /**
     *
     * @exception : 이메일 중복체크(duplicateEmailCheck)
     * @exception : 닉네임 중복체크(duplicateNicknameCheck)
     */

    public Long signUp(SignupRequestDto signupRequestDto) {
        duplicateEmailCheck(signupRequestDto.getEmail());
        duplicateNicknameCheck(signupRequestDto.getNickname());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return memberRepository.save(Member.builder()
                        .email(signupRequestDto.getEmail())
                        .nickname(signupRequestDto.getNickname())
                        .password(encoder.encode(signupRequestDto.getPassword()))
                        .role(Role.USER)
                        .build()).getId();
    }


//    public String login(MemberLoginRequestDto) {
//        // email 없음
//        log.info("로그인 실행");
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_EMAIL));
//
//        // password 틀림
//        log.info("memberPw:{} pw:{}", member.getPassword(), password);
//        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
//            throw new ApiException(ErrorCode.INVAILD_PASSWORD);
//        }
//        // 앞에서 Exception 안났으면 토큰 발행
//        String token = JwtUtil.createJwt(member.getEmail(), secretKey, expireTiemMs);
//        return JwtUtil.createJwt(email, secretKey, expireTiemMs);
////    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("unexpected user"));
    }

    /**
     * 이메일 중복체크 (회원가입)
     */
    public boolean duplicateEmailCheck(String email) {
        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new ApiException(ErrorCode.DUPLICATE_EMAIL);
        });
        return true;
    }

    /**
     * 닉네임 중복체크 (회원가입)
     */
    public boolean duplicateNicknameCheck(String nickname) {
        memberRepository.findByNickname(nickname).ifPresent(m -> {
            throw new ApiException(ErrorCode.DUPLICATE_NICKNAME);
        });
        return true;
    }
    /**
     * 이메일 확인 (로그인)
     */





}
