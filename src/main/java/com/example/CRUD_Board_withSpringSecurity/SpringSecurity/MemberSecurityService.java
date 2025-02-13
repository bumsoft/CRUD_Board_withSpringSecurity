package com.example.CRUD_Board_withSpringSecurity.SpringSecurity;

import com.example.CRUD_Board_withSpringSecurity.Member.domain.MemberRole;
import com.example.CRUD_Board_withSpringSecurity.Member.domain.entity.Member;
import com.example.CRUD_Board_withSpringSecurity.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Member> _member = memberRepository.findByuserId(username);
        if (_member.isEmpty())
        {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Member member = _member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("wlsbum".equals(username))
        {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }
        else
        {
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getValue()));
        }

        return new User(member.getUserId(), member.getPassword(), authorities);
    }
}
