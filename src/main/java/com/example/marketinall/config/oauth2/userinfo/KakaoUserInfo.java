//package com.example.marketinall.config.oauth2.userinfo;
//
//import java.util.Map;
//
//public class KakaoUserInfo implements OAuth2UserInfo{
//
//    private Integer id;
//
//    public KakaoUserInfo(Map<String, Object> attributes) {
//        super((Map<String, Object>) attributes.get("kakao_account"));
//        this.id = (Integer) attributes.get("id");
//    }
//
//    @Override
//    public String getOAuth2Id() {
//        return this.id.toString();
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
//
//    @Override
//    public String getName() {
//        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
//    }
//}
