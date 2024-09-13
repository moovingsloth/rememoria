package com.example.rememoria.oauth;

import java.util.HashMap;
import java.util.Optional;

public class KakaoOauth2MemberInfo extends Oauth2MemberInfo {
    public KakaoOauth2MemberInfo(HashMap<String, Object> attributes) {
        super(attributes);
    }

    public String getId() {
        return attributes.get("id").toString();
    }

    public String getName() {
        HashMap<String, Object> properties = (HashMap<String, Object>) attributes.get("properties");
        return (String) properties.get("nickname");
    }

    public Optional<String> getEmail() {
        HashMap<String, Object> kakaoAccount = (HashMap<String, Object>) attributes.get("kakao_account");
        return Optional.of((String) kakaoAccount.get("email"));

    }
}
