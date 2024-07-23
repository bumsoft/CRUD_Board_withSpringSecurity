package com.example.CRUD_Board_withSpringSecurity.Member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterForm {

    @Size(min = 3, max = 20, message = "ID 길이는 3~20이어야합니다.")
    @NotEmpty(message ="ID는 필수항목입니다.")
    private String userId;

    @NotEmpty(message ="비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message ="비밀번호 확인은 필수항목입니다.")
    private String password_validate;

    @Email
    private String email;

}
