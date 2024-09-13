package com.example.rememoria.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Oauth2MemberInfo {

    protected Map<String, Object> attributes;

    public Oauth2MemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract Optional<String> getEmail();

}