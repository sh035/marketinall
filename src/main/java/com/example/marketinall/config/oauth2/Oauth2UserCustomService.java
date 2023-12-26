package com.example.marketinall.config.oauth2;

import com.example.marketinall.config.auth.PrincipalDetails;
import com.example.marketinall.config.oauth2.userinfo.*;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.Role;
import com.example.marketinall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Oauth2UserCustomService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//
//        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
//
//        return processOAuth2User(oAuth2UserRequest, oAuth2User);

                // 요청을 바탕으로 유저 정보를 담은 객체 반환
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        if (provider.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
/*        else if (provider.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }*/

        String email = oAuth2UserInfo.getEmail();
        String nickname = oAuth2UserInfo.getProvider() + "_user" + UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6));

        Member member = memberRepository.findByEmail(email)
                .map(entity -> entity.update(nickname))
                .orElse(Member.builder()
                        .email(email)
                        .nickname(nickname)
                        .password(password)
                        .role(Role.USER)
                        .authProvider(provider)
                        .build());
        memberRepository.save(member);

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

//    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        //OAuth2 로그인 플랫폼 구분
//        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
//        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
//
//        //validation 해야함
//        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
//            throw new RuntimeException("Email not found from OAuth2 provider");
//        }
//
//        Member member = memberRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
//        // 이미 가입된 경우
//        if (member != null) {
//            if (!member.getAuthProvider().equals(authProvider)) {
//                throw new RuntimeException("Email already signed up.");
//            }
//            member = updateMember(member, oAuth2UserInfo);
//        }
//
//        // 가입이 안되어 있는 경우
//        else {
//            member = saveMember(authProvider, oAuth2UserInfo);
//        }
//
//        return PrincipalDetails.create(member, oAuth2UserInfo.getAttributes());
//    }
//
//    private Member saveMember(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
//
//        Member member = Member.builder()
//                .email(oAuth2UserInfo.getEmail())
//                .nickname(oAuth2UserInfo.getName())
//                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
//                .authProvider(authProvider)
//                .role(Role.USER)
//                .build();
//
//        return memberRepository.save(member);
//    }
//
//    private Member updateMember(Member member, OAuth2UserInfo oAuth2UserInfo) {
//        return memberRepository.save(member.update(oAuth2UserInfo));
//    }


}
