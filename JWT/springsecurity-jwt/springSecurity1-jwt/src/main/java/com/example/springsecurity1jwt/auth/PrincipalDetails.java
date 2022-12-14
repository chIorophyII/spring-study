package com.example.springsecurity1jwt.auth;


import com.example.springsecurity1jwt.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*
시큐리티가 /login 주소 요청이 들어오면 낚아채서 로그인을 진행시킴
로그인 진행이 완료되면 시큐리티 session을 만듦(Security ContextHolder)
오브젝트 타입 => Authentication 타입 객체
Authentication 안에 User 정보가 있어야 됨
User 오브젝트 타입 => UserDetails 타입 객체

Security Session => Authentication => UserDetails
*/
// 로그인
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final Users user;

    // 해당 user의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        // 이것도 된다
//        collect.add(new SimpleGrantedAuthority(user.getRole()));
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 1년동안 로그인 안하면 휴면계정으로 하기로 함
        // 현재시간~로그인시간 1년을 초과하면 return false;
        return true;
    }
}
