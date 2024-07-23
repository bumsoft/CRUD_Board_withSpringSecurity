package com.example.CRUD_Board_withSpringSecurity.Member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    MemberRole(String value)
    {
        this.value = value;
    }

    private String value;
}
