package com.example.rememoria.service;

import com.example.rememoria.dto.AuthTokens;
import com.example.rememoria.dto.LoginResponse;
import com.example.rememoria.entity.Member;
import com.example.rememoria.repository.MemberRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class MemberService {

    @Value("${spring.kakao.key.client-id}")
    private String kakaoClientId;

    @Value("${spring.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.kakao.key.client-secret}")
    private String kakaoClientSecret;

    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberService(MemberRepository memberRepository, AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getAccessToken(String authorizeCode) {
        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(kakaoClientId); // 발급받은 key
            sb.append("&redirect_uri=").append(kakaoRedirectUri); // 설정한 주소
            sb.append("&code=").append(authorizeCode);
            sb.append("&client_secret=").append(kakaoClientSecret); // 클라이언트 시크릿 추가

            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result.toString());

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("Error during Kakao token request: " + e.getMessage());
            e.printStackTrace();
        }
        return accessToken;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result.toString());

            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            JsonObject profile = kakaoAccount.get("profile").getAsJsonObject();

            String nickname = profile.get("nickname").getAsString();
            String email = kakaoAccount.get("email").getAsString();
            String profileImage = profile.get("profile_image_url").getAsString();

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("profile_image", profileImage);

            br.close();
        } catch (IOException e) {
            System.err.println("Error during Kakao user info request: " + e.getMessage());
            e.printStackTrace();
        }
        return userInfo;
    }

    private LoginResponse kakaoUserLogin(HashMap<String, Object> userInfo) {
        Long uid = Long.valueOf(userInfo.get("id").toString());
        String kakaoEmail = userInfo.get("email").toString();
        String nickName = userInfo.get("nickname").toString();

        Member kakaoUser = memberRepository.findByEmail(kakaoEmail).orElse(null);

        if (kakaoUser == null) {    // 회원가입
            kakaoUser = new Member();
            kakaoUser.setId(uid);
            kakaoUser.setNickname(nickName);
            kakaoUser.setEmail(kakaoEmail);
            // kakaoUser.setLoginType("kakao");
            memberRepository.save(kakaoUser);
        }
        // 토큰 생성
        AuthTokens token = authTokensGenerator.generate(uid.toString());
        return new LoginResponse(uid, nickName, kakaoEmail, token);
    }
}