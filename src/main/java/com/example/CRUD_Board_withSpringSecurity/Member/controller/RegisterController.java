package com.example.CRUD_Board_withSpringSecurity.Member.controller;

import com.example.CRUD_Board_withSpringSecurity.Member.dto.LoginForm;
import com.example.CRUD_Board_withSpringSecurity.Member.dto.MemberRegisterForm;
import com.example.CRUD_Board_withSpringSecurity.Member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RegisterController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register(MemberRegisterForm memberRegisterForm)
    {
        return "Member/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid MemberRegisterForm memberRegisterForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) //dto에 있는 오류들(@size같은)
        {
            return "Member/registerForm";
        }

        if(!memberRegisterForm.getPassword().equals(memberRegisterForm.getPassword_validate()))
        {
            bindingResult.rejectValue("password_validate", "passwordInCorrect", "비밀번호 확인이 일치하지않습니다.");
            return "Member/registerForm";
        }

        //DataIntegrityViolationException
        try{
            memberService.join(memberRegisterForm);
        }catch(DataIntegrityViolationException e){
            bindingResult.reject("duplicated_ID_Email","이미 등록된 아이디 또는 이메일입니다.");
            return "Member/registerForm";
        }catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("Register Failed",e.getMessage());
            return "Member/registerForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(LoginForm loginForm)
    {
        return "Member/loginForm";
    }
}
