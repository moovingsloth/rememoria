//JWT를 발급하고 파싱하는데 공통적으로 필요한 Secret Key나 만료시간, Claims 정보 등을 제공
package com.example.rememoria.component.jwt;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Getter
@Component
public class JwtUtils {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String KEY_ID = "id";
    public static final String KEY_NICKNAME = "nickname";

    @Value("${jwt.secret-key.access}")
    private String accessKey;

    @Value("${jwt.secret-key.refresh}")
    private String refreshKey;

    private byte[] encodedAccessKey;
    private byte[] encodedRefreshKey;

    @Value("${jwt.expired-min.access}")
    private int accessTokenExpiredMin;

    @Value("${jwt.expired-min.refresh}")
    private int refreshTokenExpiredMin;

    @PostConstruct
    private void init() {
        encodedAccessKey = Base64.getEncoder().encodeToString(accessKey.getBytes()).getBytes();
        encodedRefreshKey = Base64.getEncoder().encodeToString(refreshKey.getBytes()).getBytes();
    }
}
