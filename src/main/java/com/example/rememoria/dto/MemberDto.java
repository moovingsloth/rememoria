package com.example.rememoria.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;
}
