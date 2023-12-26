package com.example.marketinall.config.oauth2.userinfo;

import java.util.Map;

public interface OAuth2UserInfo {

    Map<String, Object> getAttributes();

    String getOAuth2Id();
    String getProvider();
    String getEmail();
    String getName();
}