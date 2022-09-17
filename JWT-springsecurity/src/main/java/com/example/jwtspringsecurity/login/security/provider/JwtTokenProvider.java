package com.example.jwtspringsecurity.login.security.provider;

import com.example.jwtspringsecurity.login.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private static final String TOKEN_PREFIX = "Bearer ";
    @Value("${jwt.secretkey}")
    String JWT_SECRET;

    // header에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    // 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(
                // The credentials that prove the principal is correct.
                userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    // 토큰 유효성 검사
    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
        } catch (SignatureException e) {
            // 서명 검증 실패
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            // 유효한 토큰이 아닌 경우
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우
            System.out.println("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            // 문자열이 null이나 공백인 경우
            System.out.println("JWT claims string is empty");
        }
        return false;

    }

}
