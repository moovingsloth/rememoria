package com.example.rememoria.controller;

import com.example.rememoria.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/login/oauth")
public class MemberController {
    @Autowired
    private MemberService ms;

    @RequestMapping("/kakao")
    public String kakaoLogin(@RequestParam String code) {
        String access_Token = ms.getAccessToken(code);
        HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));
        System.out.println("###profile_image#### : " + userInfo.get("profile_image"));

        return "member/testPage";
    }
}