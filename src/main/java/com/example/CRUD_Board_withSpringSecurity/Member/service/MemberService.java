package com.example.CRUD_Board_withSpringSecurity.Member.service;

import com.example.CRUD_Board_withSpringSecurity.Board.dto.PostResponse;
import com.example.CRUD_Board_withSpringSecurity.Common.exception.DataNotFoundException;
import com.example.CRUD_Board_withSpringSecurity.Member.dto.MemberRegisterForm;
import com.example.CRUD_Board_withSpringSecurity.Member.domain.entity.Member;
import com.example.CRUD_Board_withSpringSecurity.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(MemberRegisterForm form)
    {
        Member member = new Member();
        member.setUserId(form.getUserId());

        if(form.getEmail().isBlank())
        {
            member.setEmail(null);
        }
        else
        {
            member.setEmail(form.getEmail());
        }

        member.setPassword(passwordEncoder.encode(form.getPassword()));
        memberRepository.save(member);
        return member;
    }

    public Member getMember(String userId) throws DataNotFoundException
    {
        Optional<Member> member = memberRepository.findByuserId(userId);
        if (member.isPresent())
        {
            return member.get();
        }
        else
        {
            throw new DataNotFoundException("Member not found");
        }
    }

    public boolean isAuthor(Principal principal, PostResponse postResponse)
    {
        if(principal == null){return false;}

        String loginUser = principal.getName();
        String postWriter = postResponse.getWriter();
        return loginUser.equals(postWriter);
    }

    public boolean isAdmin(Principal principal)
    {
        if(principal==null){return false;}
        //현재 사용자의 권한 중 ROLE_ADMIN이 있는지 확인.
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

    }
}
