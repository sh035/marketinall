package com.example.marketinall.config.auth;

import com.example.marketinall.config.oauth2.userinfo.OAuth2UserInfo;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

//    private Long id;
//    private String email;
//    private Collection<? extends GrantedAuthority> authorities;
    private Member member;
    private Map<String, Object> attributes;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().name();
            }
        });
        return collect;
    }


    // 사용자의 패스워드 반환
    @Override
    public String getPassword() {
        return null;
    }

    // 사용자의 id를 반환(고유한 값)
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 사용 가능 여부 반환
     * true : 활성화됨
     * false : 활성화 안됨
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
    @Override
    public String getName() {
        return member.getNickname();
    }
//    String.valueOf(id);
}
